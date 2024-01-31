package example.brief.MyRh.dtos;

import example.brief.MyRh.Enum.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanySubscribeRequest {
    String companyId;
    SubscriptionStatus subscriptionStatus;
    String token;
}