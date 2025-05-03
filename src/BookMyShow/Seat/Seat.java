package BookMyShow.Seat;

// id, row, column, type, price, and status
public class Seat {
    private final String seatId;
    private final int row;
    private final int col;
    private final SeatType seatType;
    private double price;                                       // seats price can change in different shows(late shows can have a higher price
    private SeatStatus status;

    public Seat(String seatId, int row, int col, SeatType seatType, double price, SeatStatus status) {
        this.seatId = seatId;
        this.row = row;
        this.col = col;
        this.seatType = seatType;
        this.price = price;
        this.status = status;
    }

    public String getSeatId() {
        return seatId;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    // seat's price can change, hence we might need to set its status
    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public SeatStatus getStatus() {
        return status;
    }

    // seat's status can also change, hence we need setStatus() as well
    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
