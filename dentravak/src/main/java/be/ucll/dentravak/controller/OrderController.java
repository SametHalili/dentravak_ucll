package be.ucll.dentravak.controller;

import be.ucll.dentravak.model.Order;
import be.ucll.dentravak.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public void addOrder(@RequestBody Order order) {
        orderRepository.save(order);
    }

    @RequestMapping(value = "/sandwiches/{id}", method = RequestMethod.PUT)
    public void updateSandwich(@PathVariable("id") UUID id, @RequestBody Order updatedSandwich) {
        if(orderRepository.existsById(id) && updatedSandwich.getId().equals(id))
            orderRepository.save(updatedSandwich);
    }

    @RequestMapping(value = "/sandwiches/{id}", method = RequestMethod.DELETE)
    public void deleteSandwich(@PathVariable("id") UUID id) {
        if(orderRepository.existsById(id)) orderRepository.delete(orderRepository.findById(id).get());
    }
}
