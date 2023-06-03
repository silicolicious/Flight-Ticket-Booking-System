package database_connectivity;
import java.sql.*;

import utils.Flight;

public class FlightJDBC{
    static String successfulAddition = "\n\tFlight added successfully!";
    static String failedAddition = "\n\tFailed to add the flight!";
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
                    System.out.println(successfulAddition);
                else
                    System.out.println(failedAddition);
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
                
            } catch(Exception e){
                System.out.println(e);
            }
        }

        // TODO: Delete from table
    }

    public static void addSchedule(){
        // TODO: add a flight schedule
    }
    public static void removeSchedule(){
        // TODO: remove a flight schedule
    }
    public static void viewSchedule(){
        // TODO: view all the schedule
    }
}
