package example.brief.MyRh.entities;

import example.brief.MyRh.Enum.ConnectedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    private int tel;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ConnectedStatus connected;

    @OneToMany(mappedBy="candidate")
    private List<Postule> postule;
    @PrePersist
    @PreUpdate
    public void checkStatus(){
        if (this.connected == null || this.connected.describeConstable().isEmpty()){
            this.connected = ConnectedStatus.DISCONNECT;
        }
    }
}
