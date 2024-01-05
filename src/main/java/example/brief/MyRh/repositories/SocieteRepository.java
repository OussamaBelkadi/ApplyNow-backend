package example.brief.MyRh.repositories;

import example.brief.MyRh.entities.Societe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocieteRepository extends JpaRepository<Societe, Long> {
    Optional<Societe> findByEmail(String societe);
    Optional<Societe> findByCode(String code);

}
