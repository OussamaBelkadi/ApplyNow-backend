package example.brief.MyRh.repositories;

import example.brief.MyRh.entities.Candidate;
import example.brief.MyRh.entities.HistoriqueTest;
import example.brief.MyRh.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<HistoriqueTest, Long> {

    @Query("select t.date from HistoriqueTest t where t.candidate.id = :candidateId order by t.date DESC")
    List<LocalDate> findLast3RegistrationForCandidate(@Param("candidateId") Long candidateId);
    @Query("select t from HistoriqueTest t where t.id = :testId order by t.date DESC limit 1")
    Optional<HistoriqueTest> findLastRegistrationForCandidate(@Param("testId") Long testId);

    List<HistoriqueTest> findAllByCandidateAndAndId(Candidate candidate, Test.Id id);
}
