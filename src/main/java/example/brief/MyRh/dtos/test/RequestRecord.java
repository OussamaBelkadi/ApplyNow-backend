package example.brief.MyRh.dtos.test;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter @Setter
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class RequestRecord {
    long testId;
    long candidateId;
    int score;
}
