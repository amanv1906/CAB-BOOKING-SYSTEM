package CabBooking.src.Services;

import java.util.HashMap;
import java.util.Map;

import CabBooking.src.Exception.*;
import CabBooking.src.Models.*;
import CabBooking.src.enums_and_constants.Gender;

public class UserService {

    private static UserService userService = null;
    public Map<String, User> user_map = new HashMap<String, User>();
    public Map<String, Driver> driver_map = new HashMap<String, Driver>();
    public Map<String, Vehicle> vehicle_map = new HashMap<String, Vehicle>();

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public User add_user(String name, int age, Gender gender) throws UserAlreadyExistException, UserCreateException {
        /*
         * since we are taking user as a key according to the question
         * so we have to handle the case of dulicate other wise it will give error
         */
        if (user_map.containsKey(name)) {
            throw new UserAlreadyExistException("User is already there");
        }
        try {
            User user = new User(name, age, gender);
            user_map.put(name, user);
            return user;
        } catch (Exception e) {
            throw new UserCreateException(e.toString());
        }
    }

    public Driver add_driver(String name, int age, Gender gender, String vehicle_info, int[] location_coord)
            throws UserAlreadyExistException, UserCreateException {
        /*
         * since we are taking user as a key according to the question
         * so we have to handle the case of dulicate other wise it will give error
         */
        if (driver_map.containsKey(name)) {
            throw new UserAlreadyExistException("Driver is already there");
        }

        String[] vehicle_details = vehicle_info.split(",");
        String vehicle_name = vehicle_details[0];
        String vehicle_no = vehicle_details[1];
        Vehicle vehicle;

        if (vehicle_map.containsKey(vehicle_details[1])) {
            vehicle = vehicle_map.get(vehicle_no);
        } else {
            vehicle = new Vehicle(vehicle_name, vehicle_no);
        }
        Location location = new Location(location_coord[0], location_coord[1]);
        try {
            Driver driver = new Driver(name, age, gender, vehicle, location);
            driver_map.put(name, driver);
            return driver;
        } catch (Exception e) {
            throw new UserCreateException(e.toString());
        }
    }

    public void update_user_location(String name, int[] location_coord) throws UserNotFoundException {
        if (user_map.containsKey(name) == false) {
            throw new UserNotFoundException("User is not present in db");
        }
        User user = user_map.get(name);
        user.update_location(location_coord[0], location_coord[1]);
    }

    public void update_user_location(String name, Location current_location) throws UserNotFoundException {
        if (user_map.containsKey(name) == false) {
            throw new UserNotFoundException("User is not present in db");
        }
        User user = user_map.get(name);
        user.update_location(current_location);
    }

    public void update_driver_location(String name, int[] location_coord) throws UserNotFoundException {
        if (driver_map.containsKey(name) == false) {
            throw new UserNotFoundException("Driver is not present in db");
        }
        Driver driver = driver_map.get(name);
        driver.update_location(location_coord[0], location_coord[1]);
    }

    public void update_driver_location(String name, Location current_location) throws UserNotFoundException {
        if (driver_map.containsKey(name) == false) {
            throw new UserNotFoundException("Driver is not present in db");
        }
        Driver driver = driver_map.get(name);
        driver.update_location(current_location);
    }

    public void change_driver_status(String name, boolean status) throws UserNotFoundException {
        // if driver not found
        if (driver_map.containsKey(name) == false) {
            throw new UserNotFoundException("Driver is not present in db");
        }
        Driver driver = driver_map.get(name);
        driver.change_status(status);
    }

    public void add_amount_in_driver(String name, int amount) throws UserNotFoundException
    {
        if (driver_map.containsKey(name) == false) {
            throw new UserNotFoundException("Driver is not present in db");
        }
        Driver driver = driver_map.get(name);
        driver.add_earning(amount); 
    }

    public Map<String, Driver> get_driver_map() {
        return driver_map;
    }

    public Map<String, User> get_user_map() {
        return user_map;
    }

    public String get_drivers_earning()
    {
        String overall_income = "";
        Map<String, Driver> driver_details = driver_map;
        for (Map.Entry<String, Driver> entry : driver_details.entrySet()) {
            Driver driver = entry.getValue();
            overall_income += entry.getKey() + " earn $" + driver.get_income() + "\n";
        }
        return overall_income;
    }

}
