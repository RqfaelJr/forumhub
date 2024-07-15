package junior.rafael.forumhub.domain.topico;

import junior.rafael.forumhub.domain.autor.Autor;
import junior.rafael.forumhub.domain.autor.AutorRepository;
import junior.rafael.forumhub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TokenService tokenService;


    public Autor getAutor(String auth) {
        var token = auth.replace("Bearer ", "");
        return autorRepository.findByEmail(tokenService.getSubject(token));
    }

    public boolean verificaAutor(String auth, Long id) {
        var token = auth.replace("Bearer ", "");
        var topico = topicoRepository.getReferenceById(id);
        var autorTopico = topico.getAutor();
        var autorRequisicao = autorRepository.findByEmail(tokenService.getSubject(token));

        return autorTopico == autorRequisicao;
    }


}
