package junior.rafael.forumhub.domain.autor;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(

        @NotBlank
        String email,

        @NotBlank
        String senha


) {
}
