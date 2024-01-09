package example.brief.MyRh.repositories;

import example.brief.MyRh.entities.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat,Integer> {

}
