package junior.rafael.forumhub.domain.autor;

import jakarta.persistence.*;
import junior.rafael.forumhub.domain.resposta.Resposta;
import junior.rafael.forumhub.domain.topico.Topico;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Table(name = "usuarios")
@Entity(name = "Autor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Topico> topicos;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Resposta> respostas;

    @ManyToOne
    private Perfil perfil;

    @Transient
    private BCryptPasswordEncoder encoder;

    public Autor(DadosCadastroUsuario dados, Perfil perfil) {
        this.email = dados.email();
        this.nome = dados.nome();
        this.senha = new BCryptPasswordEncoder().encode(dados.senha());
        this.perfil = perfil;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
