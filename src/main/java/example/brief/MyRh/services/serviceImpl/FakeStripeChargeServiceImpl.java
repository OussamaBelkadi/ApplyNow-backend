package example.brief.MyRh.services.serviceImpl;


import example.brief.MyRh.entities.StripeHistory;
import example.brief.MyRh.repositories.StripHistoryRepository;
import example.brief.MyRh.services.FakeStripeChargeService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FakeStripeChargeServiceImpl implements FakeStripeChargeService {

    private  final StripHistoryRepository stripHistoryRepository;

    public FakeStripeChargeServiceImpl(StripHistoryRepository stripHistoryRepository) {
        this.stripHistoryRepository = stripHistoryRepository;
    }
    @Override
    public StripeHistory pay(Map<String, Object> params){
        StripeHistory stripeHistory = new StripeHistory();
        stripeHistory.setAmount((double) params.get("amount"));
        stripeHistory.setCompanyId((String) params.get("source"));
        stripeHistory.setCurrency((String) params.get("currency"));
        stripeHistory.setToken((String) params.get("source"));
        stripeHistory.setCreatedAt(java.time.LocalDateTime.now());
        return stripHistoryRepository.save(stripeHistory);
    };
}