package example.brief.MyRh.repositories;

import example.brief.MyRh.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
