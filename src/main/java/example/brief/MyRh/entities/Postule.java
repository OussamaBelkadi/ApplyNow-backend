package example.brief.MyRh.entities;

import example.brief.MyRh.Enum.PostuleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class Postule {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Candidat candidat;

    private String cv;
    private String motivation;

    @Enumerated(EnumType.STRING)
    private PostuleStatus postuleStatus;

    @ManyToOne
    private Offre offre;
}
