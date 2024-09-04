package org.template.orderservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.template.orderservice.client.UserServiceClient;
import org.template.orderservice.model.OrderDetails;
import org.template.orderservice.model.OrderRequest;
import org.template.orderservice.model.userservice.UserDetails;
import org.template.orderservice.repository.OrderRepository;

import java.util.UUID;

@Service
public class OrderService {

    Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    OrderRepository repository;
    @Autowired
    private UserServiceClient userClient;
    public ResponseEntity<?> setOrder(OrderRequest request){
        try {
            UserDetails userDetails = userClient.getUser(request.getCustomerId());
            System.out.println(userDetails.toString());

            OrderDetails orderDetails = OrderDetails.builder()
                    .orderedAt(request.getOrderedAt())
                    .transactionId(UUID.randomUUID().toString())
                    .amount(request.getAmount())
                    .customerId(request.getCustomerId())
                    .customerEmail(userDetails.getEmail())
                    .build();
            return ResponseEntity.ok(repository.save(orderDetails));
        }
        catch (Exception e){
            logger.info("Error in placing order {}",e.getMessage());
            throw new RuntimeException(e.getMessage());
        }


    }
}
