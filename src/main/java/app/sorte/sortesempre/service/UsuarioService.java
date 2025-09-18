package app.sorte.sortesempre.service;

import app.sorte.sortesempre.domain.Usuario;
import app.sorte.sortesempre.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  private final UsuarioRepository repo;

  public UsuarioService(UsuarioRepository repo) {
    this.repo = repo;
  }

  @Transactional
  public Usuario criar(Usuario u) {
    if (repo.existsByEmailIgnoreCase(u.getEmail())) {
      throw new IllegalArgumentException("E-mail já cadastrado");
    }
    return repo.save(u);
  }

  public Usuario buscarPorId(Long id) {
    return repo.findById(id).orElseThrow(
      () -> new EntityNotFoundException("Usuário não encontrado: id=" + id)
    );
  }

  public Page<Usuario> findAll(Pageable pageable) {
    return repo.findAll(pageable);
  }
}
