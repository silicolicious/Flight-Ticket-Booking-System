package database_connectivity;
import java.sql.*;

import utils.Flight;

public class FlightJDBC{
    static String incorrectQuery = "\n\tDatabase query error";
    static String connectionError = "\n\tDatabase connection is null";
    
    static Connection connection = null;

    public static void addFlight(Flight flight) {
        if(connection == null) 
            connection = DatabaseConnection.getConnection();
        
        if(connection != null){
            try {
                String query = "INSERT INTO Flight VALUES (?, ?, ?)";
                
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, flight.getFlightNo());
                statement.setString(2, flight.getDepartureCity());
                statement.setString(3, flight.getArrivalCity());
                int rowsInserted = statement.executeUpdate();   
                if (rowsInserted > 0)
                    System.out.println("\n\tFlight added successfully!");
                else
                    System.out.println("\n\tFailed to add the flight!");
                } catch (SQLException e) {
                    System.out.println(incorrectQuery);
                e.printStackTrace();
            }
        }
        else{
            System.out.println(connectionError);
        }
    }
    
    public static void removeFlight(int flightNo){
        if(connection == null) 
        connection = DatabaseConnection.getConnection();
            
        if(connection != null){
            try{
                String query = "DELETE FROM Flight WHERE flightNo = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, flightNo);
                int rowsInserted = statement.executeUpdate();   
                if (rowsInserted > 0)
                    System.out.println("\n\tRemoved the flight successfully!");
                else
                    System.out.println("\n\tFailed to remove the flight!");
            } catch(Exception e){
                System.out.println(incorrectQuery);
                e.printStackTrace();
            }
        }
        else{
            System.out.println(connectionError);
        }
    }

    public static void addSchedule(){
        // TODO: add a flight schedule
        {
            System.out.println(connectionError);
        }
    }
    public static void removeSchedule(){
        // TODO: remove a flight schedule
    }
    public static void viewSchedule(){
        // TODO: view all the schedule
    }
}
