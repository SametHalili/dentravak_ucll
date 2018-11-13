package be.ucll.dentravak.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String breadType;
    private UUID sandwichId;
    
    public Order () {}

    public Order(UUID id, String breadType, UUID sandwichId) {
        this.id = id;
        this.breadType = breadType;
        this.sandwichId = sandwichId;
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

    public UUID getSandwichId() {
        return sandwichId;
    }

    public void setSandwichId(UUID sandwichId) {
        this.sandwichId = sandwichId;
    }

    public static class OrderBuilder {
        private UUID id;
        private String breadType;
        private UUID sandwichId;

        public OrderBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public OrderBuilder withBreadType(String breadType) {
            this.breadType = breadType;
            return this;
        }
        
        public OrderBuilder withSandwichId(UUID sandwichId) {
            this.sandwichId = sandwichId;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setId(this.id);
            order.setBreadType(this.breadType);
            order.setSandwichId(this.sandwichId);
            return order;
        }
    }
}
