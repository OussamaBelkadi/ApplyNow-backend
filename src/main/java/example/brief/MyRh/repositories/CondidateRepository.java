package example.brief.MyRh.repositories;

import example.brief.MyRh.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CondidateRepository extends JpaRepository<Candidate, Long> {
   Optional<Candidate> findByEmail(String email);
}
