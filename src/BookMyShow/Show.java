package BookMyShow;

import BookMyShow.Seat.Seat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

// id, movie, theatre, startTime, endTime, and possible(seating arrangements)
public class Show {
    private final String showId;
    private final Movie movie;
    private final Theatre theatre;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final Map<String, Seat> seats;   // {seatId, seat}                       // this seats map represents the seat mapping of the show

    public Show(String showId, Movie movie, Theatre theatre, LocalDateTime startTime, LocalDateTime endTime, Map<String,Seat> seats) {
        this.showId = showId;
        this.movie = movie;
        this.theatre = theatre;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = seats;
    }

    public String getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Map<String,Seat> getSeats() {
        return seats;
    }
}
