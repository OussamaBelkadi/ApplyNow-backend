package example.brief.MyRh.dtos.paiement;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter @Setter
public class AccountDTO {
    @DateTimeFormat(pattern = "yy-MM-dd")
    private LocalDate date;
    private Double balance;
}
