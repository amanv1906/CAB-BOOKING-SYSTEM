package CabBooking.src;

import CabBooking.src.Services.CabService;
import CabBooking.src.Models.*;
import CabBooking.src.enums_and_constants.*;

import java.util.ArrayList;

public class CabBookingApp {
    public static void main(String[] args) {

        // this is the main class given to check the given test case in document.
        CabService cab_service = CabService.getInstance();

        // 1. On Board this User
        User user1 = null;
        User user2 = null;
        User user3 = null;

        // Create User
        try {
            user1 = cab_service.add_user("Abhishek", Gender.MALE, 23);
            user2 = cab_service.add_user("Rahul", Gender.MALE, 29);
            user3 = cab_service.add_user("Sneha", Gender.FEMALE, 22);
            cab_service.update_userLocation("Abhishek", new int[] { 0, 0 });
            cab_service.update_userLocation("Rahul", new int[] { 10, 6 });
            cab_service.update_userLocation("Sneha", new int[] { 15, 3 });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Driver driver1 = null;
        Driver driver2 = null;
        Driver driver3 = null;

        try {
            driver1 = cab_service.add_driver("Driver1", Gender.MALE, 22, "Swift, KA-01-12345",
                    new int[] { 10, 1 });
            driver2 = cab_service.add_driver("Driver2", Gender.MALE, 29, "Swift, KA-01-12345",
                    new int[] { 11, 10 });
            driver3 = cab_service.add_driver("Driver3", Gender.MALE, 24, "Swift, KA-01-12345",
                    new int[] { 5, 3 });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n-------------------Checking for Abhishek--------------------");
        try {
            ArrayList<String> drivers_list = cab_service.find_ride("Abhishek", new int[] { 0, 0 }, new int[] { 20, 1 });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            int[] rahul_start_coordinates = new int[] { 10, 0 };
            int[] rahult_dest_coordinates = new int[] { 15, 3 };

            Location start_location = new Location(rahul_start_coordinates[0], rahul_start_coordinates[1]);
            Location end_location = new Location(rahult_dest_coordinates[0], rahult_dest_coordinates[1]);
            ArrayList<String> drivers_list_rahul = cab_service.find_ride("Rahul", rahul_start_coordinates,
                    rahult_dest_coordinates);

            // taking the first driver from the drivers list if found
            System.out.println("\n-------------------------------------------");
            cab_service.choose_ride("Rahul", drivers_list_rahul.get(0), start_location, end_location);
            System.out.println("\n-------------------------------------------");
            cab_service.CalculateBill("Rahul");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n------------------checking for nandini------------------------");
        try {
            cab_service.change_driver_status("Driver1", false);
            ArrayList<String> drivers_list_Nandini = cab_service.find_ride("Nandini", new int[] { 15, 6 },
                    new int[] { 2, 4 });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("\n-------------------------------------------");
            System.out.println("Total Earning of Drivers :");
            System.out.println(cab_service.find_total_earning());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}