package console_interaction;
import java.util.Scanner;

import database_connectivity.FlightJDBC;
import model.*;

public class CustomerHomeConsole{
    static Scanner scn = new Scanner(System.in);
    static Customer currentCustomer;

    public static void searchFlights(){
        System.out.println();
        FlightJDBC.searchFlights();
    }

    public static void bookTickets(){
        System.out.println();
        System.out.println("Enter the details:-\n");
        System.out.print("Enter the schedule ID: ");
        int scheduleId = scn.nextInt();
        System.out.print("Enter the number of tickets: ");
        int noOfTickets = scn.nextInt();

        
    }
    public static void viewMyBookings(){
        // TODO: view user bookings console interaction
    }


    public static void runCustomerConsole(Customer customer) {
        System.out.println();
        currentCustomer = customer;
        System.out.println("\n\nWelcome Customer " + customer.getUsername());
        
        System.out.println();
        System.out.println("Select from the options:- ");
        System.out.println("1. Search flights");
        System.out.println("2. Book ticket");
        System.out.println("3. View my bookings");
        System.out.println("4. Logout");

        System.out.print("Enter your option: ");
        int option = scn.nextInt();

        boolean loggedIn = true;
        while(loggedIn){
            switch(option){
                case 1:
                    searchFlights();
                    break;
                case 2:
                    bookTickets();
                    break;
                case 3:
                    viewMyBookings();
                    break;
                case 4:
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again...");
            }
        }
    }
}
