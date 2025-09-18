package app.sorte.sortesempre.repository;

import app.sorte.sortesempre.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  boolean existsByEmailIgnoreCase(String email);
}
