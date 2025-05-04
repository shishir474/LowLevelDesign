package HotelManagementSystem;

public class Guest {
    private final String guestId;
    private final String name;
    private final String email;
    private final String phoneNo;

    public Guest(String guestId, String name, String email, String phoneNo) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
