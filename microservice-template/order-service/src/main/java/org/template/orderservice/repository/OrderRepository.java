package org.template.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.template.orderservice.model.OrderDetails;

public interface OrderRepository extends JpaRepository<OrderDetails, Long> {
}
