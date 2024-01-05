package example.brief.MyRh.repositories;

import example.brief.MyRh.entities.Postule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostuleRepository extends JpaRepository<Postule, Long> {
}
