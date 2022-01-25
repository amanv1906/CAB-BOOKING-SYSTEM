package CabBooking.src.Services;

import java.util.ArrayList;
import java.util.Map;

import CabBooking.src.Models.*;
import CabBooking.src.enums_and_constants.*;
import CabBooking.src.Exception.DriverNotAvailableException;
import CabBooking.src.Exception.RideNotFoundException;
import CabBooking.src.Exception.UserNotFoundException;

public class RideService {

    //create a lock object and decide what to lock down
    /*
    ride service should be thread safe if two thread book cab for the same
    time then one thread will be blocked and other thread will be able to book cab    
    */

    Object lock = new Object();
    private static RideService ride_service = null;

    public static RideService getInstance() {
        if (ride_service == null) {
            ride_service = new RideService();
        }
        return ride_service;
    }

    private static UserService user_service = UserService.getInstance();

    public Ride choose_ride(String user_name, String driver_name, Location from, Location to)
            throws UserNotFoundException, DriverNotAvailableException {
        synchronized(lock)
        {
        if (user_service.get_user_map().containsKey(user_name) == false) {
            throw new UserNotFoundException("User is not present in db");
        }
        if (user_service.get_driver_map().containsKey(driver_name) == false) {
            throw new UserNotFoundException("Rider is not present in db");
        }
        Driver driver = user_service.get_driver_map().get(driver_name);
        User user = user_service.get_user_map().get(user_name);
        if (driver.get_status() == false) {
            throw new DriverNotAvailableException("Driver is not available");
        }
        System.out.println("Ride Started");
        Ride ride = new Ride(driver, user, from, to);

        // user is on ride
        user.set_ride(ride);

        // driver is confirmed with this user so it is unvailable
        user_service.change_driver_status(driver.getName(), false);
        return ride;
    }
    }

    public void calculate_bill(String name)
            throws UserNotFoundException, RideNotFoundException {
        synchronized(lock){
        if (user_service.get_user_map().containsKey(name) == false) {
            throw new UserNotFoundException("User is not present in db");
        }

        User user = user_service.get_user_map().get(name);
        if (user.get_current_ride() == null) {
            throw new RideNotFoundException("Bill Cannot be calculated as user is not in any ride");
        }
        Ride ride = user.get_current_ride();
        System.out.println(ride.get_ride_details());
        end_ride(ride);
    }
    }

    // it is public function so should only not be accessible outside class
    private ArrayList<Driver> get_available_drivers(int user_x_coord, int user_y_coord)
            throws DriverNotAvailableException {
        synchronized(lock){
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        Map<String, Driver> driver_map = user_service.get_driver_map();
        for (Map.Entry<String, Driver> entry : driver_map.entrySet()) {
            Driver driver = entry.getValue();
            Location driver_location = driver.get_Location();
            int driver_x_coord = driver_location.get_x();
            int driver_y_coord = driver_location.get_y();
            if (check_distance(user_x_coord, user_y_coord, driver_x_coord, driver_y_coord)) {
                drivers.add(driver);
            }
        }
        
        return drivers;
    }

    }

    public ArrayList<String> find_rides(String name, int[] start_location, int[] end_location)
            throws DriverNotAvailableException {
        ArrayList<String> drivers_name_list = new ArrayList<String>();
        ArrayList<Driver> drivers_list = get_available_drivers(start_location[0], start_location[1]);

        if (drivers_list.size() == 0) {
            throw new DriverNotAvailableException(
                    "No ride found [Since all the driver are more than 5 units away from user]");
        }

        // if the user is the radius but unavailable case
        for (int i = 0; i < drivers_list.size(); i++) {
            if (drivers_list.get(i).get_status() == true) {
                drivers_name_list.add(drivers_list.get(i).getName());
            }
        }
        if (drivers_name_list.size() == 0) {
            throw new DriverNotAvailableException(
                    "No ride found [Driver Set to unavailable]");
        }
        return drivers_name_list;
    }

    private boolean check_distance(int user_x, int user_y, int driver_x, int driver_y) {
        int dist = EuclidieanDistance.get_distance(user_x, user_y, driver_x, driver_y);
        if (dist <= MaxDistRadius.get_distance_radius()) {
            return true;
        }
        return false;
    }

    private void end_ride(Ride ride) throws UserNotFoundException {
        Driver driver = ride.get_Driver();
        User user = ride.get_user();
        user_service.update_user_location(user.get_name(), ride.get_end_Location());
        user_service.update_driver_location(driver.get_name(), ride.get_end_Location());
        user_service.add_amount_in_driver(driver.get_name(), ride.get_ride_fare());
        user.end_ride();
    }

}
