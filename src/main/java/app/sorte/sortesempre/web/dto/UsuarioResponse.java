package app.sorte.sortesempre.web.dto;

import java.time.OffsetDateTime;

public record UsuarioResponse(
  Long id,
  String nome,
  String email,
  OffsetDateTime createdAt,
  OffsetDateTime updatedAt
) {}
