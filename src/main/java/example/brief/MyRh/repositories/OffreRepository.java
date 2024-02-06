package example.brief.MyRh.repositories;

import example.brief.MyRh.entities.Offre;
import example.brief.MyRh.entities.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Long>, JpaSpecificationExecutor<Offre> {

    List<Offre> findAllBySociete(Societe societe);
    List<Offre> findAllByTitre(String Titre);

    Long countAllBySocieteId(Long id);

}
