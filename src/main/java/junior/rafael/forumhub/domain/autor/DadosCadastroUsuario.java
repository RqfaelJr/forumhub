package junior.rafael.forumhub.domain.autor;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(

        @NotBlank
        String nome,

        @NotBlank
        String email,

        @NotBlank
        String senha

) {
}