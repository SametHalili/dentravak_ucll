package be.ucll.dentravak.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Sandwich {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String ingredients;
    private BigDecimal price;

    public Sandwich(UUID id, String name, String ingredients, BigDecimal price) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public Sandwich() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static class SandwichBuilder {
        private Sandwich sandwich;

        public SandwichBuilder() {
            sandwich = new Sandwich();
        }

        public static SandwichBuilder makeSandwich() {
            return new SandwichBuilder();
        }

        public SandwichBuilder withId(UUID id) {
            sandwich.setId(id);
            return this;
        }

        public SandwichBuilder withName(String name) {
            sandwich.setName(name);
            return this;
        }

        public SandwichBuilder withIngredients(String ingredients) {
            sandwich.setIngredients(ingredients);
            return this;
        }

        public SandwichBuilder withPrice(BigDecimal price) {
            sandwich.setPrice(price);
            return this;
        }

        public Sandwich build() {
            return sandwich;
        }
    }
}
