package CarRentalSystem;

public class Car {
    private final String model;
    private final String licensePlate;
    private final String make;
    private final int year;
    private boolean available;                                              // this is bound to change, hence not final
    private final double rentalPricePerDay;

    public Car(String make, String model, int year, String licensePlate, double rentalPricePerDay){
        this.model = model;
        this.make = make;
        this.year = year;
        this.licensePlate = licensePlate;
        this.rentalPricePerDay = rentalPricePerDay;
        this.available = true;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", make='" + make + '\'' +
                ", year=" + year +
                ", available=" + available +
                ", rentalPricePerDay=" + rentalPricePerDay +
                '}';
    }

    public String getModel(){
        return model;
    }

    public String getMake(){
        return make;
    }

    public int getYear(){
        return year;
    }

    public String getLicensePlateNo(){
        return licensePlate;
    }

    public double getRentalPricePerDay(){
        return rentalPricePerDay;
    }

    public boolean isAvailable(){
        return available;
    }

    public void setAvailable(boolean available){
        this.available = available;
    }

}
