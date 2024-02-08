package example.brief.MyRh.dtos.test;

import example.brief.MyRh.entities.Candidate;
import example.brief.MyRh.entities.Speciality;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseRegisterTest {
    Candidate candidate;
    Speciality speciality;
}
