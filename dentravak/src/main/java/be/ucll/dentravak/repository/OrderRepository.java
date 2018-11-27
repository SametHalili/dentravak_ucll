package be.ucll.dentravak.repository;

import be.ucll.dentravak.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {
    @Override
    List<Order> findAll();

    List<Order> findAllByCreationDate(LocalDateTime date);
}
