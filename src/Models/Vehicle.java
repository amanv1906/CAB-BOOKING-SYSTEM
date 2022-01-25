package CabBooking.src.Models;

public class Vehicle {
    private String name;
    private String vehicle_no;

    public Vehicle(String name, String vehicle_no) {
        this.name = name;
        this.vehicle_no = vehicle_no;
    }
    
    public void set_vehicle_no(String vehicle_no)
    {
        this.vehicle_no = vehicle_no;
    }
    public void set_name(String name)
    {
        this.name = name;
    }

    public String get_name()
    {
        return name;
    }

    public String get_vehicle_no()
    {
        return vehicle_no;
    }
}
