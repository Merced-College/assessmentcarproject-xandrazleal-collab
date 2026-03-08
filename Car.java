//Xandra Leal
//3/7/2026

public class Car {

    private String brand;
    private String model;
    private int year;
    private final String color;
    private String fuelType;
    private double mileageKmpl;

    //Car_ID,Brand,Model,Year,Fuel_Type,Color,Mileage_kmpl

    public Car(String carID, String brand, String model, int year, String fuelType, String color, double mileageKmpl) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.color = color;
        this.mileageKmpl = mileageKmpl;
        

    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getColor() {
        return color;
    }

    public double getMileageKmpl() {
        return mileageKmpl;
    }

    public String toString() {
        return String.format("%s %s (%d) - %s, %s, %.2f kmpl", brand, model, year, fuelType, color, mileageKmpl);
    }



}   