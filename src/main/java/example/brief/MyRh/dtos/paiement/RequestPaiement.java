package example.brief.MyRh.dtos.paiement;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestPaiement {
    private AccountDTO accountDTO;
    private Long candidateId;
}
