package example.brief.MyRh.repositories;

import example.brief.MyRh.entities.Candidate;
import example.brief.MyRh.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    @Query("select t.date from Test  t where t.candidate.id = :candidateId order by t.date DESC")
    List<LocalDate> findLast3RegistrationForCandidate(@Param("candidateId") Long candidateId);
}
