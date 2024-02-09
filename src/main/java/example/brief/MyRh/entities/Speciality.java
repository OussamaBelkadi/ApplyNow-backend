package example.brief.MyRh.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "speciality", uniqueConstraints = {
                                @UniqueConstraint(columnNames = "name")
                            }
)
@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Speciality {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "speciality")
    private Set<Test> categorizedItems = new HashSet<>();

}
