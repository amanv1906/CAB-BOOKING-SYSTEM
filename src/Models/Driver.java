package CabBooking.src.Models;

import CabBooking.src.enums_and_constants.*;

public class Driver extends Person {
    private Vehicle vehicle;
    private Location location;
    private boolean is_available;
    private int total_earning;

    public Driver(String name, int age, Gender gender, Vehicle vehicle, Location location) {
        super(name, age, gender);
        this.vehicle = vehicle;
        this.location = location;
        this.is_available = true;
        this.total_earning = 0;
    }

    public void update_location(int x, int y) {
        location.set_x(x);
        location.set_y(y);
    }

    public void update_location(Location current_Location) {
        this.location = current_Location;
    }

    public void change_status(boolean status) {
        this.is_available = status;
    }

    public boolean get_status() {
        return is_available;
    }

    public void add_earning(int amount) {
        this.total_earning += amount;
    }

    public int get_income() {
        return this.total_earning;
    }

    public String get_vehicle_info() {
        return "Vehicle details: [Name -" + this.vehicle.get_name() + ", Number: " + this.vehicle.get_vehicle_no() +"]";
    }

    public Location get_Location() {
        return location;
    }
}
