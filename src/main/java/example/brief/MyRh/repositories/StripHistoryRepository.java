package example.brief.MyRh.repositories;

import example.brief.MyRh.entities.StripeHistory;
import org.springframework.data.repository.CrudRepository;

public interface StripHistoryRepository extends CrudRepository<StripeHistory,Long> {}
