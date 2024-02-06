package example.brief.MyRh.repositories;

import example.brief.MyRh.Enum.ConnectedStatus;
import example.brief.MyRh.Enum.PostuleStatus;
import example.brief.MyRh.Enum.StatusOffre;
import example.brief.MyRh.entities.Offre;
import example.brief.MyRh.entities.Postule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostuleRepository extends JpaRepository<Postule, Long> {
    List<Postule> findAllByOffre(Offre offre);

    List<Postule> findAllByOffreAndPostuleStatus(Offre offre, ConnectedStatus connectedStatus);



    @Query("SELECT COUNT(e) FROM Postule e WHERE e.postuleResponse = :value AND e.offre.id = :id")
    Long statistics(@Param("value") StatusOffre value, @Param("id") Long id);




}
