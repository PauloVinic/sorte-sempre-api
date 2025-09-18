package app.sorte.sortesempre.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(
  @NotBlank @Size(max = 120) String nome,
  @NotBlank @Email @Size(max = 255) String email
) {}
