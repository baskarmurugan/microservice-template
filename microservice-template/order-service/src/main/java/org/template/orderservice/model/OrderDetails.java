package org.template.orderservice.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "order_service")
@Data
@Builder
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "amount")
    private String amount;

    @Column(name = "ordered_at")
    private String orderedAt;

    @Column(name = "customer_email")
    private String customerEmail;



}
