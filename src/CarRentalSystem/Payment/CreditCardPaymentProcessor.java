package CarRentalSystem.Payment;

public class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount){
        // process payment via credit card
        // ...
        return true;
    }
}
