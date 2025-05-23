package com.delivery.delivery_api.repository;

import com.delivery.delivery_api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
