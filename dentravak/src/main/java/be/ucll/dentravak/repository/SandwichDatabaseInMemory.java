package be.ucll.dentravak.repository;

import be.ucll.dentravak.model.Sandwich;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class SandwichDatabaseInMemory {
    private List<Sandwich> sandwiches;

    public SandwichDatabaseInMemory() {
        sandwiches = Arrays.asList(new Sandwich.SandwichBuilder()
                        .withId(UUID.randomUUID())
                        .withName("Gezond")
                        .withIngredients("tomaat, sla")
                        .withPrice(BigDecimal.valueOf(4.10)).build(),
                new Sandwich.SandwichBuilder()
                        .withId(UUID.randomUUID())
                        .withName("mozzarella")
                        .withIngredients("mozzarella, tomaat")
                        .withPrice(BigDecimal.valueOf(4.50)).build());
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }
}
