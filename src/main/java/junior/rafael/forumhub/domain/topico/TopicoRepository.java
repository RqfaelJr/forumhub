package junior.rafael.forumhub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Modifying
    @Query("DELETE FROM Topico t where t.id = :id")
    void deleteById(Long id);
}
