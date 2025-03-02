import java.sql.Time;
import java.util.Date;

public class Appointment {
    private int appointmentID;
    private Date date;
    private Time time;
    private String status;
    private int donorID;
    private int receiverID;

    public Appointment(int appointmentID, Date date, Time time, String status, int donorID, int receiverID) {
        this.appointmentID = appointmentID;
        this.date = date;
        this.time = time;
        this.status = status;
        this.donorID = donorID;
        this.receiverID = receiverID;
    }

    public void displayInfo() {
        System.out.println("\nAppointment Details:");
        System.out.println("Appointment ID: " + appointmentID);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Status: " + status);
        System.out.println("Donor ID: " + donorID);
        System.out.println("Receiver ID: " + receiverID);
    }
}
