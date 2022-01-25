package CabBooking.src.Services;

import java.util.ArrayList;
import CabBooking.src.enums_and_constants.*;
import CabBooking.src.Exception.*;
import CabBooking.src.Models.*;

public class CabService {
    private static CabService cab_service = null;

    public static CabService getInstance() {
        if (cab_service == null) {
            cab_service = new CabService();
        }
        return cab_service;
    }

    // user services
    private static UserService user_service = UserService.getInstance();
    private static RideService ride_service = RideService.getInstance();

    public User add_user(String name, Gender gender, int age)
            throws UserAlreadyExistException, UserCreateException {
        return user_service.add_user(name, age, gender);
    }

    public void update_userLocation(String name, int[] location_coordinates)
            throws UserNotFoundException {
        user_service.update_user_location(name, location_coordinates);

    }

    public Driver add_driver(String name, Gender gender, int age, String vehicle_info, int[] location_coordinates)
            throws UserAlreadyExistException, UserCreateException {
        return user_service.add_driver(name, age, gender, vehicle_info, location_coordinates);
    }

    public void update_driverLocation(String name, int[] location_coordinates)
            throws UserNotFoundException {
        user_service.update_driver_location(name, location_coordinates);

    }

    public void change_driver_status(String name, boolean status)
            throws UserNotFoundException {
        user_service.change_driver_status(name, status);
    }

    public ArrayList<String> find_ride(String name, int[] start_location, int[] end_location)
            throws DriverNotAvailableException {
        return ride_service.find_rides(name, start_location, end_location);
    }

    public Ride choose_ride(String user_name, String driver_name, Location start_location, Location end_location)
            throws UserNotFoundException, DriverNotAvailableException {
        return ride_service.choose_ride(user_name, driver_name, start_location, end_location);
    }

    public void CalculateBill(String name)
            throws UserNotFoundException, DriverNotAvailableException, RideNotFoundException {
            ride_service.calculate_bill(name);
    }

    public String find_total_earning()
    {
        return user_service.get_drivers_earning();
    }

}
