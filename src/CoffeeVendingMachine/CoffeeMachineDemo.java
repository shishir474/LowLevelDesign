package CoffeeVendingMachine;

public class CoffeeMachineDemo {

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = CoffeeMachine.getInstance();

        // display menu
        coffeeMachine.displayMenu();

        //  simulate user requests
        Coffee espresso = coffeeMachine.selectCoffee("Espresso");
        coffeeMachine.dispenseCoffee(espresso, new Payment(3.5));

        Coffee Americano = coffeeMachine.selectCoffee("Americano");
        coffeeMachine.dispenseCoffee(Americano, new Payment(4.5));

        Coffee Latte = coffeeMachine.selectCoffee("Latte");
        coffeeMachine.dispenseCoffee(Latte, new Payment(2.5));


    }

}
