public abstract class User {
    private int id;
    private String name;
    private String contactInfo;

    public User(int id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public abstract void displayInfo();
}
