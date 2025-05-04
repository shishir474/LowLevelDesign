package HotelManagementSystem.payment;

public class CashPayment implements Payment{
    @Override
    public boolean processPayment(double amount){
        // process payment
        return true;
    }
}
