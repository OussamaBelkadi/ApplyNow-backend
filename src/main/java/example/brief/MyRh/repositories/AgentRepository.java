package example.brief.MyRh.repositories;

import example.brief.MyRh.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, String> {

}
