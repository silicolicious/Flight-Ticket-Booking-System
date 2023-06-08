package database_connectivity;

import java.sql.*;

import com.mysql.cj.xdevapi.Result;

import model.*;

public class BookingJDBC {
    static String incorrectQuery = "\n\tDatabase query error";
    static String connectionError = "\n\tDatabase connection is null";
    
    static Connection connection = null;

    private static boolean reserveSeats(Booking booking){
        if(connection == null) 
            connection = DatabaseConnection.getConnection();
        
        if(connection != null){
            try {
                String query = "SELECT availableSeats FROM Schedule WHERE scheduleId = " + booking.getScheduleId();
                
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);   
                if(resultSet.next()){
                    int availableSeats = resultSet.getInt("availableSeats");
                    if(availableSeats >= booking.getNoOfTickets()){
                        int remainingSeats = availableSeats - booking.getNoOfTickets();
                        String query2 = "UPDATE Schedule SET availableSeats = " + remainingSeats + " WHERE scheduleId = " + booking.getScheduleId();
                        Statement statement2 = connection.createStatement();
                        int rowsUpdated = statement2.executeUpdate(query2);   
                        if(rowsUpdated > 0)
                            return true;
                        else
                            return false;
                    }
                    else{
                        return false;
                    }
                }
            } catch (SQLException e) {
                System.out.println(incorrectQuery);
                e.printStackTrace();
                return false;
            }
        }
        else{
            System.out.println(connectionError);
        }
        return false;
    }

    private static boolean unreserveSeats(int bookingId){
        if(connection == null) 
            connection = DatabaseConnection.getConnection();
        
        if(connection != null){
            try {
                String query = "SELECT * FROM Booking WHERE bookingId = " + bookingId;
                
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);   
                resultSet.next();
                int noOfTickets = resultSet.getInt("noOfTickets");
                int scheduleId = resultSet.getInt("scheduleId");
                
                String query2 = "SELECT availableSeats FROM Schedule where scheduleId = " + scheduleId;
                Statement statement2 = connection.createStatement();
                ResultSet resultSet2 = statement2.executeQuery(query2); 
                resultSet2.next();  
                int availableSeats = resultSet2.getInt("availableSeats");
                int updatedSeatsCount = noOfTickets + availableSeats;
                
                String query3 = "UPDATE Schedule SET availableSeats = " + updatedSeatsCount + " WHERE scheduleId = " + scheduleId;
                Statement statement3 = connection.createStatement();
                int rowsUpdated = statement3.executeUpdate(query3);   
                if(rowsUpdated > 0)
                    return true;
                else
                    return false;
                
            } catch (SQLException e) {
                System.out.println(incorrectQuery);
                e.printStackTrace();
                return false;
            }
        }
        else{
            System.out.println(connectionError);
        }
        return false;
    }

    public static void addBooking(Booking booking){
        if(connection == null) 
            connection = DatabaseConnection.getConnection();
        
        if(connection != null){
            try {
                if(!reserveSeats(booking)){
                    System.out.println("\n\tNumber of seats available is insufficient!");
                    return;
                }

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
                if(!unreserveSeats(bookingId)){
                    System.out.println("\n\tCould not unreserve the seats!");
                    return;
                }

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
