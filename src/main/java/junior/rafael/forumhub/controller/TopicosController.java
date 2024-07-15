package junior.rafael.forumhub.controller;

import jakarta.validation.Valid;
import junior.rafael.forumhub.domain.autor.AutorRepository;
import junior.rafael.forumhub.domain.curso.CursoRepository;
import junior.rafael.forumhub.domain.topico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService service;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder, @RequestHeader("Authorization") String auth) {
        var curso = cursoRepository.findByNome(dados.curso());
        var autor = service.getAutor(auth);
        var topico = new Topico(dados, curso, autor);
        topicoRepository.save(topico);
        var uri = uriBuilder.path("/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault Pageable paginacao) {
        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(new DadosListagemTopico(topicoRepository.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarTopico dados, @RequestHeader("Authorization") String auth) {
        if (service.verificaAutor(auth, dados.id())) {
            var topico = topicoRepository.getReferenceById(dados.id());
            topico.atualizarDados(dados.status());
            return ResponseEntity.ok(new DadosListagemTopico(topico));
        }

        throw new RuntimeException("Não é possível atualizar tópico de outra pessoa!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id, @RequestHeader("Authorization") String auth) {
        if (service.verificaAutor(auth, id)) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new RuntimeException("Não é possível atualizar tópico de outra pessoa!");
    }

}
