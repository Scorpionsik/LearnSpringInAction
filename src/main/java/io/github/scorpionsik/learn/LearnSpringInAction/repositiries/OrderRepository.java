package io.github.scorpionsik.learn.LearnSpringInAction.repositiries;

import io.github.scorpionsik.learn.LearnSpringInAction.models.ShawarmaOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<ShawarmaOrder, Long> {
    List<ShawarmaOrder> findByDeliveryZip(String zip);
}
