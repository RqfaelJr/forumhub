package junior.rafael.forumhub.domain.resposta;

import jakarta.persistence.*;
import junior.rafael.forumhub.domain.autor.Autor;
import junior.rafael.forumhub.domain.topico.Topico;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity(name = "Resposta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;

    @ManyToOne
    private Topico topico;
    private LocalDateTime data;

    @ManyToOne
    private Autor autor;

    private String solucao;
}
