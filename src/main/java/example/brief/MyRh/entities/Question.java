package example.brief.MyRh.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Question")
@Getter @Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String qst;

    @OneToMany(mappedBy = "question")
    private List<Response> responseList;

    @ManyToOne
    private Speciality speciality;

}