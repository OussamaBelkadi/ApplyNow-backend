package example.brief.MyRh.services;

import example.brief.MyRh.Enum.SubscriptionStatus;
import example.brief.MyRh.dtos.CompanySubscribeResponse;

public interface CompanySubscriptionService {
    SubscriptionStatus getSubscriptionStatus(String companyId);
    boolean subscribe(String companyId, SubscriptionStatus subscriptionStatus , String token);
    CompanySubscribeResponse pay(String companyId, SubscriptionStatus subscriptionStatus , String token);
    boolean unsubscribe(String companyId);
}
