package be.ucll.dentravak.repository;

import be.ucll.dentravak.model.Sandwich;

import java.util.List;

public class SandwichRepository {
    private SandwichDatabaseInMemory sandwichDatabaseInMemory = new SandwichDatabaseInMemory();

    public List<Sandwich> getAll() {
        return sandwichDatabaseInMemory.getSandwiches();
    }
}
