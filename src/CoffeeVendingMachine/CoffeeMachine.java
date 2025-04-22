package CoffeeVendingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachine {
    private static final CoffeeMachine instance = new CoffeeMachine();
    private final List<Coffee> coffeeMenu;
    private final HashMap<String, Ingredients> ingredients;

    private CoffeeMachine(){
        coffeeMenu = new ArrayList<>();
        ingredients = new HashMap<>();
        initialiseIngredients();
        initialiseCoffeeMenu();
    }

    public static CoffeeMachine getInstance(){
        return instance;
    }

    private void initialiseIngredients(){
        ingredients.put("Coffee", new Ingredients("Coffee", 10));
        ingredients.put("Milk", new Ingredients("Milk", 10));
        ingredients.put("Water", new Ingredients("Water", 10));
    }

    private void initialiseCoffeeMenu() {
        Map<Ingredients, Integer> espressoRecipe = new HashMap<>();
        espressoRecipe.put(ingredients.get("Coffee"), 2);
        espressoRecipe.put(ingredients.get("Water"), 3);
        espressoRecipe.put(ingredients.get("Milk"), 3);
        coffeeMenu.add(new Coffee("Espresso", 3.5, espressoRecipe));

        Map<Ingredients, Integer> americanoRecipe = new HashMap<>();
        americanoRecipe.put(ingredients.get("Coffee"), 3);
        americanoRecipe.put(ingredients.get("Water"), 4);
        americanoRecipe.put(ingredients.get("Milk"), 3);
        coffeeMenu.add(new Coffee("Americano", 2.5, americanoRecipe));

        Map<Ingredients, Integer> latteRecipe = new HashMap<>();
        latteRecipe.put(ingredients.get("Coffee"), 3);
        latteRecipe.put(ingredients.get("Water"), 1);
        latteRecipe.put(ingredients.get("Milk"), 2);
        coffeeMenu.add(new Coffee("Latte", 3.0, latteRecipe));

    }

    public void displayMenu(){
        System.out.println("Coffee Menu !");
        for(Coffee coffee: coffeeMenu){
            System.out.println(coffee.getName() + " - $" + coffee.getPrice());
        }
    }

    public Coffee selectCoffee(String coffeeName){
        for(Coffee c : coffeeMenu){
            if (c.getName().equalsIgnoreCase(coffeeName)){
                return c;
            }
        }
        return null;
    }

    public synchronized void dispenseCoffee(Coffee coffee, Payment payment){
        if(coffee.getPrice() <= payment.getAmount()){
            if(hasEnoughIngredients(coffee)){
                updateIngredients(coffee);
                System.out.println("Dispensing " + coffee.getName());
                double change = payment.getAmount() - coffee.getPrice();
                if(change > 0){
                    System.out.println("Please collect your change: $ " + change);
                }
            }
            else{
                System.out.println("Insufficient ingredients to make " + coffee.getName());
            }
        }
        else{
            System.out.println("Insufficient payment for " + coffee.getName());
        }
    }

    private boolean hasEnoughIngredients(Coffee coffee){
        for(Map.Entry<Ingredients,Integer> entry : coffee.getRecipe().entrySet()){
            Ingredients ingredient = entry.getKey();
            Integer requiredQty = entry.getValue();

            if(requiredQty > ingredient.getQuantity()){
                return false;
            }
        }

        return true;
    }

    private void updateIngredients(Coffee coffee){
        for(Map.Entry<Ingredients,Integer> entry : coffee.getRecipe().entrySet()){
            Ingredients ingredient = entry.getKey();
            Integer requiredQty = entry.getValue();
            ingredient.updateQuantity(requiredQty);
            if(ingredient.getQuantity() < 3){
                System.out.println("Low inventory alert: " + ingredient.getName());
            }
        }
    }




}
