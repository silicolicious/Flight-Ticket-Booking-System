package console_interaction;
import java.util.*;

import database_connectivity.*;
import utils.*;

public class AgentHomeConsole{
    static Scanner scn = new Scanner(System.in);

    public static void addFlight(){
        System.out.println();
        System.out.println("Enter the flight details:-\n");
        System.out.print("Enter the flight number: ");
        int flightNo = scn.nextInt();
        scn.nextLine();
        System.out.print("Enter the departure city: ");
        String departureCity = scn.nextLine();
        System.out.print("Enter the arrival city: ");
        String arrivalCity = scn.nextLine();

        FlightJDBC.addFlight(new Flight(flightNo, departureCity, arrivalCity));
    }

    public static void removeFlight(){
        // TODO: remove a flight console interaction
    }
    public static void viewBookings(){
        // TODO: view all the bookings console interaction
    }
    public static void addSchedule(){
        // TODO: add a flight schedule console interaction
    }
    public static void removeSchedule(){
        // TODO: remove a flight schedule console interaction
    }
    public static void viewSchedule(){
        // TODO: view all the schedule console interaction
    }

    public static void runAgentConsole(Agent agent){
        Scanner scn = new Scanner(System.in);

        System.out.println();
        System.out.println("Welcome Agent " + agent.getUsername());
        
        System.out.println("Select from the options:- ");
        System.out.println("1. Add flight");
        System.out.println("2. Remove flight");
        System.out.println("3. View Schedule");

        System.out.print("Enter your option: ");
        int option = scn.nextInt();
        switch(option){
            case 1:
                addFlight();
                break;
            case 2:
                removeFlight();
                break;
            case 3:
                viewBookings();
                break;
        }
    }
}
