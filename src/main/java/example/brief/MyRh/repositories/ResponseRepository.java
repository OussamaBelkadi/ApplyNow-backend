package example.brief.MyRh.repositories;

import example.brief.MyRh.entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response,Long> {


}
