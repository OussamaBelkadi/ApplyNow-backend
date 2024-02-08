package example.brief.MyRh.dtos.societe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanySubscribeResponse {

    String companyId;
    String subscriptionStatus;
    String token;
    String message;
}