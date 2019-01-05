package be.ucll.dentravak.controller;

import be.ucll.dentravak.model.Order;
import be.ucll.dentravak.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable("id") UUID id) {
        return orderRepository.findById(id).get();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public Order addOrder(@RequestBody Order order) {
        orderRepository.save(order);
        return order;
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PUT)
    public Order updateOrder(@PathVariable("id") UUID id, @RequestBody Order updatedOrder) {
        if(orderRepository.existsById(id) && updatedOrder.getId().equals(id))
            orderRepository.save(updatedOrder);

        return updatedOrder;
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable("id") UUID id) {
        if(orderRepository.existsById(id)) orderRepository.delete(orderRepository.findById(id).get());
    }

    @RequestMapping(value = "/orders/{date}", method = RequestMethod.GET)
    public List<Order> getOrdersByDate(@PathVariable("date")LocalDateTime date) {
        return orderRepository.findAllByCreationDate(date);
    }
}
