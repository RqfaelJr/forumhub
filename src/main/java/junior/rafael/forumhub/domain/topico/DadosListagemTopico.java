package junior.rafael.forumhub.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(String titulo, String mensagem, LocalDateTime data, Estado status, String autor, String curso) {

    public DadosListagemTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getData(), topico.getStatus(), topico.getAutor().getNome(), topico.getCurso().getNome());
    }
}
