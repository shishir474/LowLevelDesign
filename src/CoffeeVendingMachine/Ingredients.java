package CoffeeVendingMachine;

public class Ingredients {
    private final String name;
    private int quantity;

    public Ingredients(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }

    public int getQuantity(){
        return quantity;
    }

    public void updateQuantity(int amount){
        quantity = quantity - amount;
    }

}
