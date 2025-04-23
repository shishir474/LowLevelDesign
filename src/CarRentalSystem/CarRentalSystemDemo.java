package CarRentalSystem;

import java.time.LocalDate;
import java.util.List;

public class CarRentalSystemDemo {

    public static void main(String[] args) {
        RentalSystem rentalSystem = RentalSystem.getInstance();

        // Add cars to the rental system
        rentalSystem.addCar(new Car("Toyota", "Camry", 2022, "MH12SL6973", 50));
        rentalSystem.addCar(new Car("Ford", "Mustang", 2021, "MH01PQ6800", 70));
        rentalSystem.addCar(new Car("Honda", "City", 2022, "MH04MN623", 40));

        // create customer
        Customer customer = new Customer("John Doe", "john@example.com", "DF1234");

        // make reservations
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(3);

        List<Car> availableCars = rentalSystem.searchCars("Toyota", "Camry", startDate, endDate);
        if(!availableCars.isEmpty()){
            Car selectedCar = availableCars.getFirst();
            Reservation reservation = rentalSystem.makeReservation(customer, selectedCar, startDate, endDate);
            if(reservation != null){
                boolean paymentStatus = rentalSystem.processPayment(reservation.getTotalPrice());
                if(paymentStatus){
                    System.out.println("TotalPrice " + reservation.getTotalPrice());
                    System.out.println(reservation);
                    System.out.println("Reservation successful. Reservation ID: " + reservation.getReservationId());
                } else{
                    System.out.println("Payment Failed. Reservation cancelled");
                    rentalSystem.cancelReservation(reservation);
                }
            } else{
                System.out.println("Selected car is not available for the given dates.");
            }
        } else{
            System.out.println("No available cars for the given criteria.");
        }



    }
}
