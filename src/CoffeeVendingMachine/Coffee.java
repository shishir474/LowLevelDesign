package CoffeeVendingMachine;

import java.util.Map;

public class Coffee {
    private final String name;
    private final double price;
    private final Map<Ingredients, Integer> recipe;

    public Coffee(String name, Double price, Map<Ingredients, Integer> recipe){
        this.name = name;
        this.price = price;
        this.recipe = recipe;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public Map<Ingredients,Integer> getRecipe(){
        return recipe;
    }
}
