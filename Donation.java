import java.util.Date;

public class Donation {
    private int donationID;
    private Date date;
    private int hospitalID;
    private int donorID;
    private int receiverID;
    private int quantity;

    public Donation(int donationID, Date date, int hospitalID, int donorID, int receiverID, int quantity) {
        this.donationID = donationID;
        this.date = date;
        this.hospitalID = hospitalID;
        this.donorID = donorID;
        this.receiverID = receiverID;
        this.quantity = quantity;
    }

    public void displayInfo() {
        System.out.println("\nDonation Details:");
        System.out.println("Donation ID: " + donationID);
        System.out.println("Donor ID: " + donorID);
        System.out.println("Receiver ID: " + receiverID);
        System.out.println("Date: " + date);
        System.out.println("Hospital ID: " + hospitalID);
        System.out.println("Quantity: " + quantity + " units");
    }
}