package be.ucll.dentravak.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sandwich {
    private String name;
    private String ingredients;
    private double price;

    public Sandwich(String name, String ingredients, double price) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public Sandwich() {}

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static class LunchBuilder {
        private String name;
        private String ingredients;
        private double price;

        private LunchBuilder() {}

        public static List<Sandwich> getListOfRandomLunches(int amountOfLunches) {
            List<Sandwich> lunches = new ArrayList<>();
            for(int i = 0; i < amountOfLunches; i++) {
                lunches.add(new Sandwich.LunchBuilder()
                        .setName("Sandwich " + i)
                        .setIngredients("bread, cheese")
                        .setPrice(i).build());
            }
            return lunches;
        }

        public LunchBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public LunchBuilder setIngredients(String ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public LunchBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Sandwich build() {
            Sandwich lunch = new Sandwich();
            lunch.setName(this.name);
            lunch.setIngredients(this.ingredients);
            lunch.setPrice(this.price);
            return lunch;
        }
    }
}
