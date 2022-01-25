package CabBooking.src.Models;

import CabBooking.src.enums_and_constants.*;

public class Ride {
    private Driver driver;
    private User user;
    private Location StartLocation;
    private Location EndLocation;

    public Ride(Driver driver, User user, Location StartLocation, Location EndLocation) {
        this.driver = driver;
        this.user = user;
        this.StartLocation = StartLocation;
        this.EndLocation = EndLocation;
    }

    public Location get_start_location() {
        return StartLocation;
    }

    public Location get_end_Location() {
        return EndLocation;
    }

    public String get_driver_name() {
        return driver.getName();
    }

    public String get_user_name() {
        return user.getName();
    }

    public String get_ride_details() {

        String s0 = "Heres your final bill for the ride " + user.get_name() + "\n";
        String s1 = "Driver Name: " + this.driver.get_name() + "\n";
        String s2 = this.driver.get_vehicle_info() + "\n";
        String s3 = "Total Fare- $" + get_ride_fare() + "\n";
        String s4 = "Thank you";

        return s0 + s1 + s2 + s3 + s4;
    }

    public Driver get_Driver() {
        return driver;
    }

    public User get_user() {
        return user;
    }

    public int get_ride_fare() {
        int x1 = StartLocation.get_x();
        int y1 = StartLocation.get_y();
        int x2 = EndLocation.get_x();
        int y2 = EndLocation.get_y();

        return EuclidieanDistance.get_distance(x1, y1, x2, y2);
    }
}
