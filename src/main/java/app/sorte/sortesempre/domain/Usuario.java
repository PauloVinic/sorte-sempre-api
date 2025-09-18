package app.sorte.sortesempre.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario",
       uniqueConstraints = @UniqueConstraint(name = "uk_usuario_email", columnNames = "email"))
public class Usuario extends BaseEntity {

  @NotBlank
  @Size(max = 120)
  @Column(name = "nome", nullable = false, length = 120)
  private String nome;

  @NotBlank
  @Email
  @Size(max = 255)
  @Column(name = "email", nullable = false, length = 255)
  private String email;

  protected Usuario() {} // JPA

  public Usuario(String nome, String email) {
    this.nome = nome;
    this.email = email;
  }

  public String getNome() { return nome; }
  public String getEmail() { return email; }

  public void setNome(String nome) { this.nome = nome; }
  public void setEmail(String email) { this.email = email; }
}
