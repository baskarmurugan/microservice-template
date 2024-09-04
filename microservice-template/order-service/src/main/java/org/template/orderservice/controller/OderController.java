package org.template.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.template.orderservice.model.OrderRequest;
import org.template.orderservice.service.OrderService;

@RestController
@RequestMapping("api/order")
public class OderController {
    @Autowired
    OrderService service;
    @PostMapping("setOrder")
    public ResponseEntity<?> setOrder(@RequestBody OrderRequest request){
        System.out.println(request.toString());
        return service.setOrder(request);
    }
}
