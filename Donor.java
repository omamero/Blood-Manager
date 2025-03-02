public class Donor {
    private int id;
    private String name;
    private int age;
    private String contactInfo;
    private String address;
    private String bloodType;

    public Donor(int id, String name, int age, String contactInfo, String address, String bloodType) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.contactInfo = contactInfo;
        this.address = address;
        this.bloodType = bloodType.toUpperCase();
    }

    // Getters
    public int getId() { return id; }
    public String getBloodType() { return bloodType; }

    @Override
    public String toString() {
        return String.format("Donor [ID=%d, Name=%s, Blood=%s]", id, name, bloodType);
    }
}