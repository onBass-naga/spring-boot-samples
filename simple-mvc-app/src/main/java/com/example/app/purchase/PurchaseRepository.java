package com.example.app.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by naga on 2015/02/04.
 */
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
