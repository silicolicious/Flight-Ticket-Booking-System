package database_connectivity;

import java.sql.*;
import model.*;

public class BookingJDBC {
    static String incorrectQuery = "\n\tDatabase query error";
    static String connectionError = "\n\tDatabase connection is null";
    
    static Connection connection = null;

    public static void addBooking(Booking booking){
        if(connection == null) 
            connection = DatabaseConnection.getConnection();
        
        if(connection != null){
            try {
                String query = "INSERT INTO Booking (userId, scheduleId, noOfTickets) VALUES (?, ?, ?)";
                
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, booking.getUserId());
                statement.setInt(2, booking.getScheduleId());
                statement.setInt(3, booking.getNoOfTickets());
                int rowsInserted = statement.executeUpdate();   
                if (rowsInserted > 0)
                    System.out.println("\n\tBooking successfully completed!");
                else
                    System.out.println("\n\tFailed to book the tickets!");
                } catch (SQLException e) {
                    System.out.println(incorrectQuery);
                e.printStackTrace();
            }
        }
        else{
            System.out.println(connectionError);
        }
    }

    public static void cancelBooking(int bookingId){
        if(connection == null) 
            connection = DatabaseConnection.getConnection();
        
        if(connection != null){
            try {
                String query = "DELETE FROM Booking WHERE bookingId = " + bookingId;
                
                Statement statement = connection.createStatement();
                int rowsDeleted = statement.executeUpdate(query);   
                if(rowsDeleted > 0)
                    System.out.println("\n\tBooking cancelled successfully!");
                else
                    System.out.println("\n\tFailed to cancel the booking!");
                } catch (SQLException e) {
                    System.out.println(incorrectQuery);
                e.printStackTrace();
            }
        }
        else{
            System.out.println(connectionError);
        }
    }

    public static void viewAllBookings(){
        if(connection == null) 
            connection = DatabaseConnection.getConnection();
            
        if(connection != null){
            try{
                String query = "SELECT * FROM Booking";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);   
                System.out.println("Booking ID\tUser ID\tSchedule ID\tNo. Of Tickets");
                while(resultSet.next()) {
                    int bookingId = resultSet.getInt("bookingId");
                    int userId = resultSet.getInt("userId");
                    int scheduleId = resultSet.getInt("scheduleId");
                    int noOfTickets = resultSet.getInt("noOfTickets");

                    System.out.println( bookingId + "\t" + userId + "\t" + scheduleId + "\t" + noOfTickets);
                }
            } catch(Exception e){
                System.out.println(incorrectQuery);
                e.printStackTrace();
            }
        }
        else{
            System.out.println(connectionError);
        }
    }

    public static void viewMyBookings(int userId){
        if(connection == null) 
            connection = DatabaseConnection.getConnection();
            
        if(connection != null){
            try{
                String query = "SELECT * FROM Booking WHERE userId = " + userId;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);   
                System.out.println("Booking ID\tSchedule ID\tNo. Of Tickets");
                while(resultSet.next()) {
                    int bookingId = resultSet.getInt("bookingId");
                    int scheduleId = resultSet.getInt("scheduleId");
                    int noOfTickets = resultSet.getInt("noOfTickets");

                    System.out.println(bookingId + "\t" + scheduleId + "\t" + noOfTickets);
                }
            } catch(Exception e){
                System.out.println(incorrectQuery);
                e.printStackTrace();
            }
        }
        else{
            System.out.println(connectionError);
        }
    }
}
