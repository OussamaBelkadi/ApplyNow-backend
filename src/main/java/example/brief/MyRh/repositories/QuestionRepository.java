package example.brief.MyRh.repositories;

import example.brief.MyRh.entities.Question;
import example.brief.MyRh.entities.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    List<Question> findAllBySpeciality(Speciality speciality);
    @Query("SELECT Q FROM Question Q WHERE Q.speciality = :speciality ORDER BY FUNCTION('RANDOM') LIMIT 10")
    List<Question> findAllBySpec(Speciality speciality);


}