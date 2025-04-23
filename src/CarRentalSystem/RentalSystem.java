package CarRentalSystem;

import CarRentalSystem.Payment.CreditCardPaymentProcessor;
import CarRentalSystem.Payment.PaymentProcessor;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RentalSystem {
    private final static RentalSystem instance = new RentalSystem();
    // private final List<Car> carslist;                                             // instead of maintaining list of cars, lets maintain a map of licensePlateNo and Car for O(1) retrieval
    private final Map<String,Car> cars;
    // private final List<Reservation> reservations;
    private final Map<String, Reservation> reservations;                           // instead of maintaining a list of reservations, maintain a map of reservation_id and reservation object
    private final PaymentProcessor paymentProcessor;


    private RentalSystem(){
        cars = new ConcurrentHashMap<>();
        reservations = new ConcurrentHashMap<>();
        paymentProcessor = new CreditCardPaymentProcessor();
    }

    public static synchronized RentalSystem getInstance(){
        return instance;
    }

    public void addCar(Car car){
        cars.put(car.getLicensePlateNo(), car);
    }

    public void removeCar(Car car){
        cars.remove(car.getLicensePlateNo());
    }

    public synchronized List<Car> searchCars(String make, String model, LocalDate startDate, LocalDate endDate){
        List<Car> availableCars = new ArrayList<>();
        // iterate over all the cars and compare it's make,model with the one in the map
        for(Car car : cars.values()){
            if(car.getMake().equalsIgnoreCase(make) && car.getModel().equalsIgnoreCase(model) && car.isAvailable()){
                if(isCarAvailable(car, startDate, endDate)){
                    availableCars.add(car);
                }
            }
        }
        return availableCars;
    }

    private boolean isCarAvailable(Car car, LocalDate startDate, LocalDate endDate){
        // checks the availability of the car in the given date range
        for(Reservation reservation: reservations.values()){
            if(reservation.getCar().equals(car)){
                if (!(endDate.isBefore(reservation.getStartDate()) || startDate.isAfter(reservation.getEndDate())) ){
                    return false;
                }
            }
        }
        return true;
    }

    //    manage reservations: Create new reservation, Modify reservation, Cancel Reservation
    public synchronized Reservation makeReservation(Customer customer, Car car, LocalDate startDate, LocalDate endDate){
        // car should be available for the specified duration, then only you can make this reservation + payment success
        if(isCarAvailable(car, startDate, endDate)){
            String reservationId = generateReservationId();
            Reservation reservation = new Reservation(reservationId, startDate, endDate, customer, car);
            reservations.put(reservationId, reservation);
            car.setAvailable(false);
            return reservation;
        }
        return null;
    }

    private String generateReservationId(){
        return "RES" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }


//    public void modifyReservation(Reservation reservation){
//        out of scope for now
//        based on the input from the user, modify the reservation
//    }

    public synchronized void cancelReservation(Reservation reservation){
        Reservation cancelledReservation = reservations.remove(reservation.getReservationId());
        if(cancelledReservation != null){
            cancelledReservation.getCar().setAvailable(true);
        }
    }

    public synchronized boolean processPayment(double amount){
        return paymentProcessor.processPayment(amount);
    }

}
