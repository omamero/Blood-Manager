public class BloodStock {
    private int hospitalId;
    private String bloodType;
    private int quantity;

    public BloodStock(int hospitalId, String bloodType, int quantity) {
        this.hospitalId = hospitalId;
        this.bloodType = bloodType.toUpperCase();
        this.quantity = quantity;
    }

    // Getters
    public String getBloodType() { return bloodType; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return String.format("BloodStock [Hospital=%d, Blood=%s, Qty=%d]", 
                           hospitalId, bloodType, quantity);
    }
}