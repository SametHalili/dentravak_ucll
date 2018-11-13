package be.ucll.dentravak.repository;

import be.ucll.dentravak.model.Sandwich;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SandwichRepository extends CrudRepository<Sandwich, UUID> {
    @Override
    List<Sandwich> findAll();
}
