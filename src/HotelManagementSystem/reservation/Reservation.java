package HotelManagementSystem.reservation;

import HotelManagementSystem.Guest;
import HotelManagementSystem.room.Room;

import java.time.LocalDate;

public class Reservation {
    private final String reservationId;
    private final Guest guest;
    private final Room room;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
//    private final double totalPrice;                      // not tracking price
    private ReservationStatus reservationStatus;

    public Reservation(String reservationId, Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate, ReservationStatus reservationStatus) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
//        this.totalPrice = totalPrice;
        this.reservationStatus = reservationStatus;
    }

    public synchronized void cancel(){
        if(reservationStatus == ReservationStatus.Confirmed){
            reservationStatus = ReservationStatus.Cancelled;
            room.checkOut();
        }
        else{
            throw new IllegalStateException("Reservation is not confirmed");
        }
    }

    public String getReservationId() {
        return reservationId;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }
}
