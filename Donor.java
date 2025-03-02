public class Donor extends User {
    private int age;
    private String address;
    private String bloodType;

    public Donor(int id, String name, String contactInfo, int age, String address, String bloodType) {
        super(id, name, contactInfo);
        this.age = age;
        this.address = address;
        this.bloodType = bloodType;
    }

    public String getBloodType() {
        return bloodType;
    }

    @Override
    public void displayInfo() {
        System.out.println("\nDonor Details:");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Contact Info: " + getContactInfo());
        System.out.println("Age: " + age);
        System.out.println("Address: " + address);
        System.out.println("Blood Type: " + bloodType);
    }
}
