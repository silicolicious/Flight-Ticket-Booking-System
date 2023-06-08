package database_connectivity;

import java.sql.*;

import model.*;

public class ScheduleJDBC{
    static String incorrectQuery = "\n\tDatabase query error";
    static String connectionError = "\n\tDatabase connection is null";
    
    static Connection connection = null;
    
    public static void addSchedule(Schedule schedule){
        if(connection == null) 
            connection = DatabaseConnection.getConnection();
        
        if(connection != null){
            try {
                String query = "INSERT INTO Schedule (flightNo, startTime, endTime, availableSeats) VALUES (?, ?, ?, ?)";
                
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(2, schedule.getFlight());
                statement.setString(3, schedule.getStartTime().toString());
                statement.setString(4, schedule.getEndTime().toString());
                statement.setInt(5, schedule.getAvailableSeats());
                int rowsInserted = statement.executeUpdate();   
                if (rowsInserted > 0)
                    System.out.println("\n\tSchedule added successfully!");
                else
                    System.out.println("\n\tFailed to add the schedule!");
            } catch (SQLException e) {
                System.out.println(incorrectQuery);
                e.printStackTrace();
            }
        }
        else{
            System.out.println(connectionError);
        }
    }
    
    public static void removeSchedule(int scheduleId){
        if(connection == null) 
            connection = DatabaseConnection.getConnection();
        
        if(connection != null){
            try {
                String query = "DELETE FROM Schedule WHERE ScheduleId = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, scheduleId);
                int rowsDeleted = statement.executeUpdate();   
                if (rowsDeleted > 0)
                    System.out.println("\n\tSchedule removed successfully!");
                else
                    System.out.println("\n\tFailed to remove the schedule!");
            } catch (SQLException e) {
                System.out.println(incorrectQuery);
                e.printStackTrace();
            }
        }
        else{
            System.out.println(connectionError);
        }
    }
    
    public static void viewSchedule(){
        if(connection == null) 
        connection = DatabaseConnection.getConnection();
            
        if(connection != null){
            try{
                String query = "SELECT * FROM Flight INNER JOIN Schedule ON Flight.flightNo = Schedule.flightNo";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);   
                System.out.println("Schedule ID\tFlight No.\tStart\tDestination\tDeparture Time\tArrival Time\tAvailable Seats");
                while(resultSet.next()) {
                    int scheduleId = resultSet.getInt("scheduleId");
                    int flightNo = resultSet.getInt("flightNo");
                    String departureCity = resultSet.getString("departureCity");
                    String arrivalCity = resultSet.getString("arrivalCity");

                    String startTime = resultSet.getString("startTime");
                    String endTime = resultSet.getString("endTime");

                    int availableSeats = resultSet.getInt("availableSeats");
                    System.out.print(scheduleId + "\t" + flightNo + "\t" + departureCity + "\t" + arrivalCity + "\t");
                    System.out.println(startTime + "\t" + endTime + "\t" + availableSeats);
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
