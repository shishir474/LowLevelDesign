package CarRentalSystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private final String reservationId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double totalPrice;
    private final Customer customer;
    private final Car car;


    public Reservation(String reservationId, LocalDate startDate, LocalDate endDate, Customer customer, Car car){
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.car = car;
        this.customer = customer;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice(){
        long daysRented = ChronoUnit.DAYS.between(startDate,endDate) + 1;
        return daysRented * car.getRentalPricePerDay();
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalPrice=" + totalPrice +
                ", customer=" + customer +
                ", car=" + car +
                '}';
    }

    public String getReservationId(){
        return reservationId;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public Customer getCustomer(){
        return customer;
    }

    public Car getCar(){
        return car;
    }




}
