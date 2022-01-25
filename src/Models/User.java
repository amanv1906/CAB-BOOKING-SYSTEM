package CabBooking.src.Models;

import CabBooking.src.enums_and_constants.*;

public class User extends Person {
    private Location location;
    private Ride ride=null;

    public User(String name, int age, Gender gender)
    {
        super(name,age,gender);
        location = new Location(0,0);
    }

    public void update_location(int x, int y)
    {
        location.set_x(x);
        location.set_y(y);
    }

    public void update_location(Location current_Location)
    {
        this.location = current_Location;
    }

    public void set_ride(Ride ride)
    {
        this.ride = ride;
    }

    public void end_ride()
    {
        this.ride = null;
    }

    public Ride get_current_ride()
    {
        return ride;
    }
}
