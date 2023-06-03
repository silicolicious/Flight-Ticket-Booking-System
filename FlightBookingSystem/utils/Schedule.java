package utils;
import java.sql.Timestamp;

public class Schedule extends Flight{
    int scheduleId;
    int flight;
    Timestamp startTime;
    Timestamp endTime;
    int availableSeats;

    public Schedule(int flightNo, String departureCity, String arrivalCity, int scheduleId,
            Timestamp startTime, Timestamp endTime, int availableSeats) {
        super(flightNo, departureCity, arrivalCity);
        this.scheduleId = scheduleId;
        this.flight = flightNo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.availableSeats = availableSeats;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setFlight(int flight) {
        this.flight = flight;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public int getFlight() {
        return flight;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    @Override
    public String toString() {
        return "Schedule [scheduleId=" + scheduleId + ", flight=" + flight + ", startTime=" + startTime + ", endTime="
                + endTime + ", availableSeats=" + availableSeats + "]";
    }
}
