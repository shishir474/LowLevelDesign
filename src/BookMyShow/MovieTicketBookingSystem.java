package BookMyShow;

import BookMyShow.Booking.Booking;
import BookMyShow.Booking.BookingStatus;
import BookMyShow.Seat.Seat;
import BookMyShow.Seat.SeatStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MovieTicketBookingSystem {
    private static MovieTicketBookingSystem instance;
    private final List<Movie> movies;
    private final List<Theatre> theatres;
    private final Map<String, Show> shows;                        // {showId --> Show}
    private final Map<String, Booking> bookings;                  // {bookingId --> Booking}

    private static final AtomicLong bookingCounter = new AtomicLong(0);
    private static final String BOOKING_ID_PREFIX = "BKG";

    public MovieTicketBookingSystem(){
        movies = new ArrayList<>();
        theatres = new ArrayList<>();
        shows = new ConcurrentHashMap<>();
        bookings = new ConcurrentHashMap<>();
    }

    public static synchronized MovieTicketBookingSystem getInstance(){
        if(instance == null){
            instance = new MovieTicketBookingSystem();
        }
        return instance;
    }

    public void addMovie(Movie movie){
        movies.add(movie);
    }

    public void addTheatre(Theatre theatre){
        theatres.add(theatre);
    }

    public void addShow(Show show){
        shows.put(show.getShowId(), show);
    }

    // flow"
        // areSeatsAvailable(List<Seats> selectedSeats) -- if yes, markSeatsBooked() --> updates the status of the selected seats to Booked --> calculate total amount --> create a booking object and insert it into bookings map
        // Note initially booking is done with status "Pending"
    // first check if the selectedSeats are available for the given show
    // if yes, update the status of the selectedSeats to Booked, calculate the totalprice, make a booking(initially all booking's status will be kept as Pending)
    public synchronized Booking bookTickets(User user, Show show, List<Seat> selectedSeats){
        if(areSeatsAvailable(show,selectedSeats)){      // all selectedSeats exists and are available for Show show
            markSeatsBooked(show,selectedSeats);        // reserve the seats
            double totalPrice = calculateTotalPrice(selectedSeats);         // calculate total
            String bookingId = generateBookingId();     // create a booking object
            Booking booking = new Booking(bookingId, user, show, selectedSeats, totalPrice, BookingStatus.Pending);
            bookings.put(bookingId, booking);           // add booking object to bookings map
            return booking;
        }
        return null;            // seats not available, hence booking not possible
    }

    //  Utility Method
    private boolean areSeatsAvailable(Show show, List<Seat> selectedSeats){
        for(Seat seat: selectedSeats){
            // check if the seat is available in this show. Get the seat in this show with the help of seatId and check its status
            Seat selectedSeat = show.getSeats().get(seat.getSeatId());
            if(selectedSeat==null || selectedSeat.getStatus()==SeatStatus.Booked){
                return false;
            }
        }
        return true;
    }

    //  Utility Method
    private void markSeatsBooked(Show show, List<Seat> selectedSeats){
        for(Seat seat: selectedSeats){
            // marked this seat's status as Booked in this Show
            // first get that seat from the map & then update is's status
            Seat showSeat = show.getSeats().get(seat.getSeatId());
            showSeat.setStatus(SeatStatus.Booked);
        }
    }

    //  Utility Method
    private double calculateTotalPrice(List<Seat> selectedSeats){
        return selectedSeats.stream().mapToDouble(Seat::getPrice).sum();

//        double total = 0;
//        for(Seat seat: selectedSeats) total += seat.getPrice();
//        return total;
    }


    // confirm the booking, once the payment is confirmed
    // booking status can be either of these three - [Pending, Confirmed, Cancelled]
    // here it is assumed that payment is always successfull
    public synchronized void confirmBooking(String bookingId){
        Booking booking = bookings.get(bookingId);
        // get the booking from the bookingId;
        if(booking != null && booking.getStatus()==BookingStatus.Pending){
            // process payment and send confirmation
            // ...
            booking.setStatus(BookingStatus.Confirmed);
        }
    }

    //  Utility Method
    private String generateBookingId(){
        long bookingNumber = bookingCounter.incrementAndGet();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        // I'll use a combination of Timestamp(YYYYMMDDHHMMSS) + bookingCounter
        // 	String.format("%06d", bookingNumber) converts the booking number to a 6-digit string, padded with leading zeroes if needed.
        return BOOKING_ID_PREFIX + timestamp + String.format("%06d", bookingNumber);
    }

    // Only that booking can be cancelled, whose status is Pending/Confirmed
    // Once a booking is cancelled, we also need to update the status of the seats part of that booking to Available
    public synchronized void cancelBooking(String bookingId){
        Booking booking = bookings.get(bookingId);
        // system can cancel confirmed and pending bookings
        if(booking != null && booking.getStatus() != BookingStatus.Cancelled){
            booking.setStatus(BookingStatus.Cancelled);
            markSeatsFree(booking.getShow(), booking.getSelectedSeats());
        }
    }

    //  Utility Method
    private void markSeatsFree(Show show, List<Seat> selectedSeats){
        for(Seat seat: selectedSeats){
           Seat showSeat = show.getSeats().get(seat.getSeatId());
           showSeat.setStatus(SeatStatus.Available);
        }
    }






}
