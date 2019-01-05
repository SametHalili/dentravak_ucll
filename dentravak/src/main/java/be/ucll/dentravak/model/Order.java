package be.ucll.dentravak.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "sandwich_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID sandwichId;
    private String name;
    private BreadType breadType;
    private BigDecimal price;
    private String mobilePhoneNumber;
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;
    private boolean printed = false;

    public enum BreadType {
        BOTERHAMMEKES,
        TURKS_BROOD,
        WRAP
    }

    public boolean isPrinted() {
        return printed;
    }

    public void setPrinted(boolean printed) {
        this.printed = printed;
    }

    public Order () {}

    public Order(UUID id, UUID sandwichId, String name, BreadType breadType, BigDecimal price, String mobilePhoneNumber, LocalDateTime creationDate) {
        this.id = id;
        this.breadType = breadType;
        this.sandwichId = sandwichId;
        this.price = price;
        this.name = name;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.creationDate = creationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }

    public UUID getSandwichId() {
        return sandwichId;
    }

    public void setSandwichId(UUID sandwichId) {
        this.sandwichId = sandwichId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public static class OrderBuilder {
        private UUID id;
        private BreadType breadType;
        private UUID sandwichId;
        private String name;
        private BigDecimal price;
        private String mobilePhone;
        private LocalDateTime creationDate;

        public static OrderBuilder buildOrder() {
            return new OrderBuilder();
        }

        public OrderBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public OrderBuilder withBreadType(BreadType breadType) {
            this.breadType = breadType;
            return this;
        }

        public OrderBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public OrderBuilder withName(String name) {
            this.name = name;
            return this;
        }
        
        public OrderBuilder withSandwich(UUID sandwichId) {
            this.sandwichId = sandwichId;
            return this;
        }

        public OrderBuilder withMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
            return this;
        }

        public OrderBuilder withDateTime(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setId(this.id);
            order.setBreadType(this.breadType);
            order.setSandwichId(this.sandwichId);
            order.setName(this.name);
            order.setPrice(this.price);
            order.setMobilePhoneNumber(this.mobilePhone);
            order.setCreationDate(this.creationDate);
            return order;
        }
    }
}
