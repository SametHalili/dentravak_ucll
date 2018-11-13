package be.ucll.dentravak.controller;

import be.ucll.dentravak.model.Order;
import be.ucll.dentravak.repository.OrderRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping("/orders")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
