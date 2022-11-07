package com.icecreamshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.icecreamshop.model.domain.Basket;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    boolean existsBySessionId(String sessionId);

    Optional<Basket> findBySessionId(String sessionId);
}
