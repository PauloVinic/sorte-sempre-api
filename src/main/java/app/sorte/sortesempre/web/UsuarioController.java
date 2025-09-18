package app.sorte.sortesempre.web;

import app.sorte.sortesempre.domain.Usuario;
import app.sorte.sortesempre.service.UsuarioService;
import app.sorte.sortesempre.web.dto.UsuarioRequest;
import app.sorte.sortesempre.web.dto.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

  private final UsuarioService service;

  public UsuarioController(UsuarioService service) {
    this.service = service;
  }

  @PostMapping
  @Transactional
  public ResponseEntity<UsuarioResponse> criar(@RequestBody @Valid UsuarioRequest req) {
    var salvo = service.criar(new Usuario(req.nome(), req.email()));
    return ResponseEntity
      .created(URI.create("/api/v1/usuarios/" + salvo.getId()))
      .body(toResponse(salvo));
  }

  @GetMapping("/{id}")
  public UsuarioResponse buscarPorId(@PathVariable Long id) {
    return toResponse(service.buscarPorId(id));
  }

  @GetMapping
  public Page<UsuarioResponse> listar(@PageableDefault(size = 20) Pageable pageable) {
    return service.findAll(pageable).map(this::toResponse);
  }

  private UsuarioResponse toResponse(Usuario u) {
    return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail(), u.getCreatedAt(), u.getUpdatedAt());
  }
}
