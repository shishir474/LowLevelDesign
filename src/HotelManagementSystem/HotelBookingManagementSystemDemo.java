package HotelManagementSystem;

import HotelManagementSystem.payment.CashPayment;
import HotelManagementSystem.reservation.Reservation;
import HotelManagementSystem.room.Room;
import HotelManagementSystem.room.RoomStatus;
import HotelManagementSystem.room.RoomType;

import java.time.LocalDate;

public class HotelBookingManagementSystemDemo {
    public static void main(String[] args) {
        HotelBookingManagementSystem bookingSystem = HotelBookingManagementSystem.getInstance();

        // Add rooms
        Room room1 = new Room("R1", RoomType.DOUBLE, RoomStatus.AVAILABLE, 150);
        Room room2 = new Room("R2", RoomType.DELUXE, RoomStatus.AVAILABLE, 200);
        bookingSystem.addRoom(room1);
        bookingSystem.addRoom(room2);

        // Add guests
        Guest guest1 = new Guest("G1", "John Doe", "john@example.com", "1234567899");
        Guest guest2 = new Guest("G2", "Anton Jena", "anton@example.com", "1234543439");
        bookingSystem.addGuest(guest1);
        bookingSystem.addGuest(guest2);

        System.out.println("Before reservation room's status: " + room1.getRoomStatus());

        Reservation res1 = bookingSystem.makeReservation(guest1, room1, LocalDate.now(), LocalDate.now().plusDays(3));
        if(res1 != null){
            System.out.println("reservation confirmed. Reservation ID: " + res1.getReservationId());
        }
        Reservation res2 = bookingSystem.makeReservation(guest2, room2, LocalDate.now(), LocalDate.now().plusDays(3));

        if(res2 != null){
            System.out.println("reservation confirmed. Reservation ID: " + res2.getReservationId());
        }
        else{
            System.out.println("Room not available for booking");
        }

        // res1 status
        System.out.println(res1.getReservationStatus());

        // check in
        System.out.println("Before checkin room's status: " + room1.getRoomStatus());
        bookingSystem.checkIn(res1.getReservationId());
        System.out.println("After checkin room's status: " + room1.getRoomStatus());
        System.out.println("Checked in: " + res1.getReservationId());

        // check out
        bookingSystem.checkOut(res1.getReservationId(), new CashPayment());
        System.out.println("After checkout room's status: " + room1.getRoomStatus());
        System.out.println("Checked out: " + res1.getReservationId());

        // cancel reservation
        System.out.println("before cancellation, reservation's status: " + res1.getReservationStatus());
        System.out.println("before cancellation, room's status: " + res1.getRoom().getRoomStatus());
        bookingSystem.cancelReservation(res1.getReservationId());
        System.out.println("after cancellation, reservation's status: " + res1.getReservationStatus());
        System.out.println("after cancellation, room's status: " + res1.getRoom().getRoomStatus());
    }
}
