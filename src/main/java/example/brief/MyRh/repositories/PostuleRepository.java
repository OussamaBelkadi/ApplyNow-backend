package example.brief.MyRh.repositories;

import example.brief.MyRh.Enum.PostuleStatus;
import example.brief.MyRh.entities.Offre;
import example.brief.MyRh.entities.Postule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostuleRepository extends JpaRepository<Postule, Long> {
    List<Postule> findAllByOffre(Offre offre);

    List<Postule> findAllByOffreAndPostuleStatus(Offre offre, PostuleStatus postuleStatus);

}
