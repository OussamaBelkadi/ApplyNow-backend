package example.brief.MyRh.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Immutable
@Getter @Setter
@NoArgsConstructor
public class Test {
    @Embeddable
    public static class Id implements Serializable {
        @Column(name = "candidate_id")
        private Long candidateId;
        @Column(name = "speciality_id")
        private Long specialityId;
        public Id(){

        }
        public Id(Long candidateId, Long specialityId){
            this.candidateId = candidateId;
            this.specialityId = specialityId;
        }
    }

    @EmbeddedId
    private Id id = new Id();
    @Column(updatable = false)
    @NotNull

    private String name;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "candidate_id",  insertable = false, updatable = false)
    private Candidate candidate;


    @ManyToOne()
    @JoinColumn(name = "speciality_id" , insertable = false, updatable = false)
    private Speciality speciality;

    public Test(String name, LocalDate date, Candidate candidate, Speciality  speciality){
        this.name = name;
        this.date = date;
        this.candidate = candidate;
        this.speciality = speciality;
        this.id.candidateId = candidate.getId();
        this.id.specialityId = speciality.getId();

    }
}
