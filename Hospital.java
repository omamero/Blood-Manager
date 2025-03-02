public class Hospital {
    private int hospitalID;
    private String name;
    private String location;
    private String contactInfo;

    public Hospital(int hospitalID, String name, String location, String contactInfo) {
        this.hospitalID = hospitalID;
        this.name = name;
        this.location = location;
        this.contactInfo = contactInfo;
    }

    public void displayInfo() {
        System.out.println("\nHospital Details:");
        System.out.println("Hospital ID: " + hospitalID);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Contact Info: " + contactInfo);
    }
}