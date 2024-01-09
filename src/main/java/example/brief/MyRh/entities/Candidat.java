package example.brief.MyRh.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Candidat")
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String fullname;
    private int tel;

    private String email;
    private String password;
    @OneToMany(mappedBy="candidat")
    private List<Postule> postule;

}
