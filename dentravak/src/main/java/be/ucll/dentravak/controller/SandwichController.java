package be.ucll.dentravak.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import be.ucll.dentravak.model.Sandwich;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SandwichController {
    @RequestMapping("/lunches")
    public List<Sandwich> getLunches() {
        return Arrays.asList(new Sandwich.SandwichBuilder()
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
}