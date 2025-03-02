public class Receiver {
    private int id;
    private String name;
    private int age;
    private String contactInfo;
    private String address;
    private String bloodType;
    private String medicalCondition;

    public Receiver(int id, String name, int age, String contactInfo, 
                   String address, String bloodType, String medicalCondition) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.contactInfo = contactInfo;
        this.address = address;
        this.bloodType = bloodType.toUpperCase();
        this.medicalCondition = medicalCondition;
    }

    // Getters
    public String getBloodType() { return bloodType; }
    
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Receiver [ID=%d, Name=%s, Blood=%s]", id, name, bloodType);
    }
}