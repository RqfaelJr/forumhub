package junior.rafael.forumhub.controller;

import jakarta.validation.Valid;
import junior.rafael.forumhub.domain.autor.*;
import junior.rafael.forumhub.infra.security.DadosTokenJWT;
import junior.rafael.forumhub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DadosLogin dados) {

            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            var authentication = manager.authenticate(authenticationToken);
            var tokenJWT = tokenService.gerarToken((Autor) authentication.getPrincipal());
            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

    }

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {

        var usuario = autorRepository.save(new Autor(dados, perfilRepository.getReferenceById(1L)));
        var uri = uriBuilder.path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var usuario = autorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

}
