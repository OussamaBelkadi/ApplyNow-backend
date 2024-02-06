package example.brief.MyRh.repositories;

import example.brief.MyRh.entities.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfoEntity,Long> {


        Optional<UserInfoEntity> findByEmail(String email);

}
