package app.sorte.sortesempre.web;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, Object> body = base(HttpStatus.BAD_REQUEST, "Erro de validação");
    Map<String, String> fields = new HashMap<>();
    for (var err : ex.getBindingResult().getFieldErrors()) {
      fields.put(err.getField(), err.getDefaultMessage());
    }
    body.put("fields", fields);
    return ResponseEntity.badRequest().body(body);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraint(ConstraintViolationException ex) {
    Map<String, Object> body = base(HttpStatus.BAD_REQUEST, "Violação de restrição");
    body.put("detail", ex.getMessage());
    return ResponseEntity.badRequest().body(body);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
    Map<String, Object> body = base(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(body);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleNotFound(EntityNotFoundException ex) {
    Map<String, Object> body = base(HttpStatus.NOT_FOUND, ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Object> handleDataIntegrity(DataIntegrityViolationException ex) {
    Map<String, Object> body = base(HttpStatus.CONFLICT, "Violação de integridade de dados");
    body.put("detail", ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGeneric(Exception ex) {
    Map<String, Object> body = base(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno");
    body.put("detail", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }

  private Map<String, Object> base(HttpStatus status, String title) {
    Map<String, Object> m = new HashMap<>();
    m.put("timestamp", OffsetDateTime.now());
    m.put("status", status.value());
    m.put("error", status.getReasonPhrase());
    m.put("title", title);
    return m;
  }
}
