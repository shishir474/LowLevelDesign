package HotelManagementSystem;

import HotelManagementSystem.payment.Payment;
import HotelManagementSystem.reservation.Reservation;
import HotelManagementSystem.reservation.ReservationStatus;
import HotelManagementSystem.room.Room;
import HotelManagementSystem.room.RoomStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.IllegalFormatWidthException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class HotelBookingManagementSystem {
    private static HotelBookingManagementSystem instance;
//    private final List<Guest> guestList;
//    private final List<Room> roomList;
    private final Map<String,Guest> guests;         // {guestId, guest}      Used maps instead of lists for storing guests and rooms
    private final Map<String,Room> rooms;           // {roomId, room}
    private final Map<String, Reservation> reservations;

    private static final AtomicLong reservationCounter = new AtomicLong(0);
    private static final String BOOKING_ID_PREFIX = "BKG";

    // constructor
    public HotelBookingManagementSystem() {
        this.guests = new ConcurrentHashMap<>();
        this.rooms = new ConcurrentHashMap<>();
        this.reservations = new ConcurrentHashMap<>();
    }

    // instance initialisation
    public static synchronized HotelBookingManagementSystem getInstance(){
        if(instance == null){
            instance = new HotelBookingManagementSystem();
        }
        return instance;
    }

    public synchronized void addRoom(Room room){
        rooms.put(room.getRoomId(), room);
    }

    public synchronized Room getRoom(String roomId){
        return rooms.get(roomId);
    }

    public synchronized void addGuest(Guest guest){
        guests.put(guest.getGuestId(), guest);
    }

    public synchronized Guest getGuest(String guestId){
        return guests.get(guestId);
    }


    public synchronized Reservation makeReservation(Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate){
        // first check if the room is available for the given date range
        // if room is available,
                // mark the room booked for the given date range
                // calculate the total price that guest has to pay
        // create a reservation and add it to the reservation maps(that tracks all the reservations)
        if(room.getRoomStatus() == RoomStatus.AVAILABLE) {
            room.book();
            String reservationId = getReservationId();
            Reservation reservation = new Reservation(reservationId, guest, room, checkInDate, checkOutDate, ReservationStatus.Confirmed);
            reservations.put(reservationId, reservation);
            return reservation;
        }

//        if(checkAvailability(room, checkInDate, checkOutDate)){
//            markRoomsBooked(room, checkInDate, checkOutDate);
//            double total = calculateTotal(room, checkOutDate - checkInDate);
//            String reservationId = getReservationId();
//            // what should be the status -- pending or confirmed
//            Reservation reservation = new Reservation(reservationId, guest, room, checkInDate, checkOutDate, total, ReservationStatus.Confirmed);
//            reservations.put(reservationId,reservation);
//            return reservation;
//        }
        return null;
    }

////    Utility Method
//    private boolean checkAvailability(Room room, LocalDate checkInDate, LocalDate checkOutDate){
//
//    }

////    Utility Method
//    private void markRoomsBooked(Room room, LocalDate checkInDate, LocalDate checkOutDate){
//
//    }

////    Utility Method
//    private double calculateTotal(Room room, int duration){
//        RoomType roomType = room.getRoomType();
//        if(roomType == RoomType.SINGLE) return 10*duration;
//        else if(roomType == RoomType.DOUBLE) return 20*duration;
//        else if(roomType == RoomType.DELUXE) return 30*duration;
//        else if(roomType == RoomType.SUITE) return 40*duration;
//    }

    private synchronized String getReservationId(){
        long nextValue = reservationCounter.incrementAndGet();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        return BOOKING_ID_PREFIX + timestamp + nextValue;
    }

    public synchronized void cancelReservation(String reservationId){
        Reservation reservation = reservations.get(reservationId);
        if(reservation != null){
            reservation.cancel();           // first checks if the reservationStatus is Confirmed, then changes its status to cancelled and then performs room check out
            reservations.remove(reservationId);
        }
    }

    public synchronized void checkIn(String reservationId){
        Reservation reservation = reservations.get(reservationId);
        if(reservation != null && reservation.getReservationStatus() == ReservationStatus.Confirmed){
            reservation.getRoom().checkIn();
        }
        else{
            throw new IllegalStateException("Invalid reservation or reservation not confirmed");
        }
    }

    public synchronized void checkOut(String reservationId, Payment payment){
        Reservation reservation = reservations.get(reservationId);
        if(reservation != null && reservation.getReservationStatus() == ReservationStatus.Confirmed){
            // before checkout, process payment
            Room room = reservation.getRoom();
            double amount = room.getPrice() * ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
            if(payment.processPayment(amount)){
                // perform checkout
                room.checkOut();
                reservations.remove(reservationId);
            }
            else{
                throw new IllegalStateException("Payment Failed");
            }
        }
        else{
            throw new IllegalStateException("Invalid reservation or reservation not confirmed");
        }
    }
}

