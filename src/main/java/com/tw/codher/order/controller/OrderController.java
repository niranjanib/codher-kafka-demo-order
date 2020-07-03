package com.tw.codher.order.controller;

import com.tw.codher.order.publisher.OrderPlacedEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderPlacedEventPublisher orderPlacedEventPublisher;

    @GetMapping("/orders/create")
    public void place(@RequestParam("count") int count) {
        orderPlacedEventPublisher.publish(count);
    }
}
