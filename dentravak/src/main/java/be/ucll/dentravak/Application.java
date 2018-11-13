package be.ucll.dentravak;

import be.ucll.dentravak.model.Order;
import be.ucll.dentravak.model.Sandwich;
import be.ucll.dentravak.repository.OrderRepository;
import be.ucll.dentravak.repository.SandwichRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner(SandwichRepository sandwichRepository, OrderRepository orderRepository) {
        return args -> {
            Sandwich sandwich = Sandwich.SandwichBuilder.makeSandwich().withName("Bla").withIngredients("bla, bla").withPrice(BigDecimal.valueOf(5.00)).build();
            sandwichRepository.save(sandwich);

            Order order = Order.OrderBuilder.buildOrder().withBreadType("bruin").withSandwich(sandwich).withMobilePhone("0494265426").build();
            orderRepository.save(order);
        };
    }
}