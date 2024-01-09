package example.brief.MyRh.entities;

import example.brief.MyRh.Enum.ConnectedStatus;
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
    private Candidate candidate;

    private String cv;
    private String motivation;

    @Enumerated(EnumType.STRING)
    private ConnectedStatus postuleStatus;

    @ManyToOne
    private Offre offre;
    @PrePersist
    @PreUpdate
    private void checkStatusConnected(){
        if (this.postuleStatus == null || this.postuleStatus.describeConstable().isEmpty()){
            this.postuleStatus = ConnectedStatus.DISCONNECT;
        }
    }
}
