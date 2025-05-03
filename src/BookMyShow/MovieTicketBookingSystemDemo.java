package BookMyShow;

import BookMyShow.Booking.Booking;
import BookMyShow.Seat.Seat;
import BookMyShow.Seat.SeatStatus;
import BookMyShow.Seat.SeatType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieTicketBookingSystemDemo {
    public static void main(String[] args) {
        MovieTicketBookingSystem bookingSystem =  MovieTicketBookingSystem.getInstance();

        // add Movies
        Movie movie1 = new Movie("M1","Movie 1", "Description 1", 120);
        Movie movie2 = new Movie("M2","Movie 2", "Description 2", 135);
        bookingSystem.addMovie(movie1);
        bookingSystem.addMovie(movie2);

        // add Theatres
        Theatre theatre1 = new Theatre("T1", "Theatre 1", "Location 1");
        Theatre theatre2 = new Theatre("T2", "Theatre 2", "Location 2");
        bookingSystem.addTheatre(theatre1);
        bookingSystem.addTheatre(theatre2);

        // add shows
        Show show1 = new Show("S1", movie1, theatre1, LocalDateTime.now(), LocalDateTime.now().plusMinutes(movie1.getDuration()), createSeats(10,10));
        Show show2 = new Show("S2", movie2, theatre2, LocalDateTime.now(), LocalDateTime.now().plusMinutes(movie2.getDuration()), createSeats(8,8));
        bookingSystem.addShow(show1);
        bookingSystem.addShow(show2);

        // book tickets
        User user = new User("U1", "John Doe", "john@example.com");
        User user2 = new User("U2", "Marissa Evans", "marisa@example.com");
        // 2 seats (5 and 6) from show1
        List<Seat> selectedSeats = Arrays.asList(show1.getSeats().get("1-5"), show1.getSeats().get("1-6"));

        System.out.println("Before booking seat's status");
        System.out.println(show1.getSeats().get("1-5").getStatus());
        System.out.println(show1.getSeats().get("1-6").getStatus());


        Booking booking = bookingSystem.bookTickets(user, show1, selectedSeats);
        if(booking != null){
            System.out.println("Booking Successfull. Booking ID: " + booking.getBookingId());
            bookingSystem.confirmBooking(booking.getBookingId());       // here the assumption is we always get a success payment confirmation
            System.out.println(booking.getStatus());
        }
        else{
            System.out.println("Booking failed. Seats not available");
        }

        // user2 trying to book the same seats from show1
        System.out.println("2nd user");
        System.out.println("After booking seat's status");
        System.out.println(show1.getSeats().get("1-5").getStatus());
        System.out.println(show1.getSeats().get("1-6").getStatus());

        Booking booking2 = bookingSystem.bookTickets(user2, show1, selectedSeats);
        if(booking2 != null){
            System.out.println("Booking 2 Successfull. Booking ID: " + booking2.getBookingId());
            bookingSystem.confirmBooking(booking2.getBookingId());       // here the assumption is we always get a success payment confirmation
        }
        else{
            System.out.println("Booking failed. Seats not available");
        }

        // cancel booking
        bookingSystem.cancelBooking(booking.getBookingId());
        System.out.println("Booking cancelled. Booking ID: " + booking.getBookingId());

        bookingSystem.cancelBooking(booking2.getBookingId());
    }

    private static Map<String, Seat> createSeats(int rows, int columns){
        Map<String, Seat> seats = new HashMap<>();
        for(int row=1;row<=rows;row++){
            for(int col=1;col<=columns;col++){
                String seatId = row + "-" + col;
                SeatType seatType = (row <= 2) ? SeatType.Premium : SeatType.Normal;
                double price = (seatType == SeatType.Premium) ? 200 : 150;
                Seat seat = new Seat(seatId,row,col,seatType,price, SeatStatus.Available);
                seats.put(seatId,seat);
            }
        }

        return seats;
    }
}
