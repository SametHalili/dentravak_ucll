package be.ucll.dentravak.model;

import java.util.List;

public class Lunch {
    private String name;
    private List<String> ingredients;
    private double price;

    public Lunch(String name, List<String> ingredients, double price) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public Lunch() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
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
        private List<String> ingredients;
        private double price;

        private LunchBuilder() {}

        public LunchBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public LunchBuilder setIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public LunchBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Lunch build() {
            Lunch lunch = new Lunch();
            lunch.setName(this.name);
            lunch.setIngredients(this.ingredients);
            lunch.setPrice(this.price);
        }
    }
}
