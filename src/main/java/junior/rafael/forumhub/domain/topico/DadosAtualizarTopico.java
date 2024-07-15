package junior.rafael.forumhub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTopico(
        @NotNull
        Long id,

        @NotNull
        Estado status


) {
}
