package junior.rafael.forumhub.domain.autor;

public record DadosDetalhamentoUsuario(String nome, String email, String perfil) {
    public DadosDetalhamentoUsuario(Autor usuario) {
        this(usuario.getNome(), usuario.getEmail(), usuario.getPerfil().getNome());
    }
}
