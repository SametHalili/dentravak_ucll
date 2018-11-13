package be.ucll.dentravak.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "sandwich_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String breadType;
    @OneToOne
    private Sandwich sandwich;
    private String mobilePhone;
    @CreationTimestamp
    private LocalDateTime dateTime;
    
    public Order () {}

    public Order(UUID id, String breadType, Sandwich sandwich, String mobilePhone, LocalDateTime dateTime) {
        this.id = id;
        this.breadType = breadType;
        this.sandwich = sandwich;
        this.mobilePhone = mobilePhone;
        this.dateTime = dateTime;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static class OrderBuilder {
        private UUID id;
        private String breadType;
        private Sandwich sandwichId;
        private String mobilePhone;
        private LocalDateTime dateTime;

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

        public OrderBuilder withMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
            return this;
        }

        public OrderBuilder withDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setId(this.id);
            order.setBreadType(this.breadType);
            order.setSandwich(this.sandwichId);
            order.setMobilePhone(this.mobilePhone);
            order.setDateTime(this.dateTime);
            return order;
        }
    }
}
