package be.ucll.dentravak.model;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "sandwich_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String breadType;
    @OneToOne
    private Sandwich sandwich;
    
    public Order () {}

    public Order(UUID id, String breadType, Sandwich sandwich) {
        this.id = id;
        this.breadType = breadType;
        this.sandwich = sandwich;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public Sandwich getSandwich() {
        return sandwich;
    }

    public void setSandwich(Sandwich sandwich) {
        this.sandwich = sandwich;
    }

    public static class OrderBuilder {
        private UUID id;
        private String breadType;
        private Sandwich sandwichId;

        public static OrderBuilder buildOrder() {
            return new OrderBuilder();
        }

        public OrderBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public OrderBuilder withBreadType(String breadType) {
            this.breadType = breadType;
            return this;
        }
        
        public OrderBuilder withSandwich(Sandwich sandwichId) {
            this.sandwichId = sandwichId;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setId(this.id);
            order.setBreadType(this.breadType);
            order.setSandwich(this.sandwichId);
            return order;
        }
    }
}
