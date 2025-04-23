package CarRentalSystem.Payment;

public class PaypalPaymentProcessor implements PaymentProcessor{
    @Override
    public boolean processPayment(double amount){
        // process paypal payment
        // ...
        return true;
    }
}
