package example.brief.MyRh.entities;

import example.brief.MyRh.Enum.ConnectedStatus;
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
    private String nom_complet;
    private String cv;
    private int tel;
    private String motivation;
    private ConnectedStatus postuleStatus;

    @ManyToOne
    private Offre offre;
    @ManyToOne
    private Candidate candidate;
    @PrePersist
    @PreUpdate
    private void checkStatusConnected(){
        if (this.postuleStatus == null && this.postuleStatus.describeConstable().isPresent()){
            this.postuleStatus = ConnectedStatus.DISCONNECT;
        }
    }
}
