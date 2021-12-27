package io.github.scorpionsik.learn.LearnSpringInAction.repositiries.i;

import io.github.scorpionsik.learn.LearnSpringInAction.models.ShawarmaOrder;

public interface OrderRepository {
    ShawarmaOrder save(ShawarmaOrder shawarmaOrder);
}
