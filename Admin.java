public class Admin extends User {
    private String role;
    private String loginCredentials;

    public Admin(int id, String name, String contactInfo, String role, String loginCredentials) {
        super(id, name, contactInfo);
        this.role = role;
        this.loginCredentials = loginCredentials;
    }

    @Override
    public void displayInfo() {
        System.out.println("\nAdmin Details:");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Contact Info: " + getContactInfo());
        System.out.println("Role: " + role);
        System.out.println("Login Credentials: " + loginCredentials);
    }
}