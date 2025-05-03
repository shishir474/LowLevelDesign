package BookMyShow.Booking;

import BookMyShow.Seat.Seat;
import BookMyShow.Show;
import BookMyShow.User;

import java.util.List;

// id, user, show, selectedSeats, status
public class Booking {
    private final String bookingId;
    private final User user;
    private final Show show;
    private final List<Seat> selectedSeats;
    private final double totalPrice;
    private BookingStatus status;                           /// booking status will change from pending to confirmed or cancelled. Hence not kept final

    public Booking(String bookingId, User user, Show show, List<Seat> selectedSeats, double totalPrice, BookingStatus status) {
        this.bookingId = bookingId;
        this.user = user;
        this.show = show;
        this.selectedSeats = selectedSeats;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSelectedSeats() {
        return selectedSeats;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status){
        this.status = status;
    }

}
