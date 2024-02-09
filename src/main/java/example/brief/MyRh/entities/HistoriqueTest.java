package example.brief.MyRh.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Builder
@Data @NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoriqueTest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
    @ManyToOne()
    @JoinColumn(name = "candidateId")
    Candidate candidate;

    @ManyToOne()
    @JoinColumn(name = "specialityId")
    Speciality speciality;


}
