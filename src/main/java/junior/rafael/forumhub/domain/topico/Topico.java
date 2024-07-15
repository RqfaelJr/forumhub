package junior.rafael.forumhub.domain.topico;

import jakarta.persistence.*;
import junior.rafael.forumhub.domain.autor.Autor;
import junior.rafael.forumhub.domain.curso.Curso;
import junior.rafael.forumhub.domain.resposta.Resposta;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private Estado status;
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Resposta> respostas;

    public Topico(DadosCadastroTopico dados, Curso curso, Autor autor) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.data = LocalDateTime.now();
        this.status = Estado.ABERTO;
        this.autor = autor;
        this.curso = curso;
    }

    public void atualizarDados(Estado status) {
        this.status = status;
    }
}
