package CarRentalSystem;

public class Customer {
    private final String name;
    private final String contactInfo;
    private final String driversLicenseNumber;

    public Customer(String name, String contactInfo, String driversLicenseNumber){
        this.name = name;
        this.driversLicenseNumber = driversLicenseNumber;
        this.contactInfo = contactInfo;
    }

    public String getName(){
        return name;
    }

    public String getLicenseNo(){
        return driversLicenseNumber;
    }

    public String getMobileNo(){
        return contactInfo;
    }


}
