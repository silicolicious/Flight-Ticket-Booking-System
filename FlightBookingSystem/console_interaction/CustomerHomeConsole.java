package console_interaction;
import java.util.Scanner;
import utils.*;

public class CustomerHomeConsole{
    public static void searchFlights(){
        // TODO: search flight console interaction

    }
    public static void bookTickets(){
        // TODO: book ticket console interaction
    }
    public static void viewMyBookings(){
        // TODO: view user bookings console interaction
    }


    public static void runCustomerConsole(Customer customer) {
        Scanner scn = new Scanner(System.in);

        System.out.println();
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
