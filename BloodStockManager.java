public class BloodStock {
    private int hospitalID;
    private String bloodType;
    private int quantity;

    public BloodStock(int hospitalID, String bloodType, int quantity) {
        this.hospitalID = hospitalID;
        this.bloodType = bloodType;
        this.quantity = quantity;
    }

    public String getBloodType() {
        return bloodType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void displayInfo() {
        System.out.println("\nBlood Stock Details:");
        System.out.println("Hospital ID: " + hospitalID);
        System.out.println("Blood Type: " + bloodType);
        System.out.println("Quantity: " + quantity + " units");
    }
}
