package example.brief.MyRh.services;


import example.brief.MyRh.entities.StripeHistory;

import java.util.Map;

public interface FakeStripeChargeService {
    public StripeHistory pay(Map<String, Object> params);
}