package org.template.orderservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class OrderRequest {
    private Long   id;
    private Long   customerId;
    private String transactionId;
    private String amount;
    private String orderedAt;

    @Override
    public String toString() {
        return "OrderRequest{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", transactionId='" + transactionId + '\'' +
                ", amount='" + amount + '\'' +
                ", orderedAt='" + orderedAt + '\'' +
                '}';
    }
}
