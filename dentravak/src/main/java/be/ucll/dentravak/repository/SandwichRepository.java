package be.ucll.dentravak.repository;

import be.ucll.dentravak.model.Sandwich;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SandwichRepository extends CrudRepository<Sandwich, UUID> {
    @Override
    List<Sandwich> findAll();
}
