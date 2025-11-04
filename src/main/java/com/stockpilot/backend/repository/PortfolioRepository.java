package com.stockpilot.backend.repository;

import com.stockpilot.backend.model.Portfolio;
import com.stockpilot.backend.model.User;
import com.stockpilot.backend.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findByUser(User user);
    Optional<Portfolio> findByUserAndStock(User user, Stock stock);
}
