package HotelManagementSystem.room;

public class Room {
    private final String roomId;
    private final RoomType roomType;
    private RoomStatus roomStatus;
    private final double price;

    public Room(String roomId, RoomType roomType, RoomStatus roomStatus, double price) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomStatus = roomStatus;
        this.price = price;
    }

    public synchronized void book(){
        if(roomStatus == RoomStatus.AVAILABLE){
            roomStatus = RoomStatus.BOOKED;
        }
        else{
            throw new IllegalStateException("Room is not available for booking");
        }
    }

    public synchronized void checkIn(){
        if(roomStatus == RoomStatus.BOOKED){
            roomStatus = RoomStatus.OCCUPIED;
        }
        else{
            throw new IllegalStateException("Room is not booked");
        }
    }

    public synchronized void checkOut(){
        if(roomStatus == RoomStatus.OCCUPIED){
            roomStatus = RoomStatus.AVAILABLE;
        }
        else{
            throw new IllegalStateException("Room is not occupied");
        }
    }

    public String getRoomId() {
        return roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public double getPrice() {
        return price;
    }
}
