package example.brief.MyRh.entities;

import example.brief.MyRh.Enum.ConnectedStatus;
import example.brief.MyRh.Enum.Grade;
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
    private Double balance;
    private Integer nbrPostule;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    @Enumerated(EnumType.STRING)
    private ConnectedStatus connected;

    @OneToOne(mappedBy = "candidate")
    private Account account;
    @OneToMany(mappedBy="candidate")
    private List<Postule> postule;
    @PrePersist
    @PreUpdate
    public void checkStatus(){
        if (this.connected == null || this.connected.describeConstable().isEmpty()){
            this.connected = ConnectedStatus.DISCONNECT;
        }
        if (this.nbrPostule == null ){
            this.nbrPostule = 0;
        }
        if (this.grade == null || this.grade.describeConstable().isEmpty()){
            this.grade = Grade.Standard;
        }
    }
}
