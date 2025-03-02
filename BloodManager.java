import java.util.ArrayList;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BloodManager {
    private static List<Donor> donors = new ArrayList<>();
    private static List<Receiver> receivers = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    private static List<Hospital> hospitals = new ArrayList<>();
    private static List<Donation> donations = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();
    private static List<BloodStock> bloodStocks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Blood Management System =====");
            System.out.println("1. Admin");
            System.out.println("2. Donor");
            System.out.println("3. Receiver");
            System.out.println("4. Display All Donors and Receivers");
            System.out.println("5. Display Blood Stock");
            System.out.println("6. Receiver Request Blood");
            System.out.println("7. Exit");
            System.out.print("Choose your role: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    handleAdmin();
                    break;
                case 2:
                    handleDonor();
                    break;
                case 3:
                    handleReceiver();
                    break;
                case 4:
                    displayAllDonorsAndReceivers();
                    break;
                case 5:
                    displayBloodStock();
                    break;
                case 6:
                    handleReceiverRequestBlood();
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleAdmin() {
        System.out.println("\n===== Admin Menu =====");
        System.out.print("Enter Admin ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Enter Role: ");
        String role = scanner.nextLine();
        System.out.print("Enter Login Credentials: ");
        String loginCredentials = scanner.nextLine();

        Admin admin = new Admin(id, name, contactInfo, role, loginCredentials);
        admins.add(admin);

        System.out.println("\nAdmin registered successfully!");
        admin.displayInfo();
    }

    private static void handleDonor() {
        System.out.println("\n===== Donor Menu =====");
        System.out.print("Enter Donor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Blood Type: ");
        String bloodType = scanner.nextLine();

        Donor donor = new Donor(id, name, contactInfo, age, address, bloodType);
        donors.add(donor);

        System.out.println("\nDonor registered successfully!");
        donor.displayInfo();

        // Match donor with receiver
        Receiver matchedReceiver = findMatchingReceiver(donor);
        if (matchedReceiver != null) {
            System.out.println("\nMatching Receiver Found:");
            matchedReceiver.displayInfo();

            // Schedule appointment
            System.out.println("\nScheduling Appointment...");
            System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            System.out.print("Enter Appointment Time: ");
            String time = scanner.nextLine();

            Appointment appointment = new Appointment(appointments.size() + 1, new Date(), Time.valueOf(time), "Scheduled", donor.getId(), matchedReceiver.getId());
            appointments.add(appointment);

            System.out.println("\nAppointment Scheduled Successfully!");
            appointment.displayInfo();
        } else {
            System.out.println("\nNo matching receiver found at the moment. Your blood will be saved to the stock.");

            // Save blood to stock
            BloodStock stock = new BloodStock(1, donor.getBloodType(), 1); // Assuming hospital ID is 1
            bloodStocks.add(stock);

            System.out.println("\nBlood saved to stock successfully!");
            stock.displayInfo();
        }
    }

    private static void handleReceiver() {
        System.out.println("\n===== Receiver Menu =====");
        System.out.print("Enter Receiver ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Blood Type: ");
        String bloodType = scanner.nextLine();
        System.out.print("Enter Medical Condition: ");
        String medicalCondition = scanner.nextLine();

        Receiver receiver = new Receiver(id, name, contactInfo, age, address, bloodType, medicalCondition);
        receivers.add(receiver);

        System.out.println("\nReceiver registered successfully!");
        receiver.displayInfo();
    }

    private static void displayAllDonorsAndReceivers() {
        System.out.println("\n===== All Donors =====");
        for (Donor donor : donors) {
            donor.displayInfo();
            System.out.println("-----------------------------");
        }

        System.out.println("\n===== All Receivers =====");
        for (Receiver receiver : receivers) {
            receiver.displayInfo();
            System.out.println("-----------------------------");
        }
    }

    private static void displayBloodStock() {
        System.out.println("\n===== Blood Stock =====");
        for (BloodStock stock : bloodStocks) {
            stock.displayInfo();
            System.out.println("-----------------------------");
        }
    }

    private static void handleReceiverRequestBlood() {
        System.out.println("\n===== Receiver Request Blood =====");
        System.out.print("Enter Receiver ID: ");
        int receiverId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Receiver receiver = findReceiverById(receiverId);
        if (receiver == null) {
            System.out.println("Receiver not found!");
            return;
        }

        String receiverBloodType = receiver.getBloodType();
        BloodStock matchedStock = findMatchingBloodStock(receiverBloodType);

        if (matchedStock != null) {
            System.out.println("\nMatching Blood Found in Stock:");
            matchedStock.displayInfo();

            // Find the donor who donated this blood
            Donor donor = findDonorByBloodType(matchedStock.getBloodType());
            if (donor != null) {
                System.out.println("\nDonor Details:");
                System.out.println("Donor ID: " + donor.getId());
                System.out.println("Blood Type: " + donor.getBloodType());

                // Schedule appointment
                System.out.println("\nScheduling Appointment...");
                System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                System.out.print("Enter Appointment Time: ");
                String time = scanner.nextLine();

                Appointment appointment = new Appointment(appointments.size() + 1, new Date(), Time.valueOf(time), "Scheduled", donor.getId(), receiver.getId());
                appointments.add(appointment);

                System.out.println("\nAppointment Scheduled Successfully!");
                appointment.displayInfo();
            } else {
                System.out.println("No donor found for this blood type.");
            }
        } else {
            System.out.println("No matching blood found in stock.");
        }
    }

    private static Receiver findReceiverById(int receiverId) {
        for (Receiver receiver : receivers) {
            if (receiver.getId() == receiverId) {
                return receiver;
            }
        }
        return null;
    }

    private static BloodStock findMatchingBloodStock(String bloodType) {
        for (BloodStock stock : bloodStocks) {
            if (stock.getBloodType().equals(bloodType) && stock.getQuantity() > 0) {
                return stock;
            }
        }
        return null;
    }

    private static Donor findDonorByBloodType(String bloodType) {
        for (Donor donor : donors) {
            if (donor.getBloodType().equals(bloodType)) {
                return donor;
            }
        }
        return null;
    }

    private static Receiver findMatchingReceiver(Donor donor) {
        for (Receiver receiver : receivers) {
            if (isCompatible(donor.getBloodType(), receiver.getBloodType())) {
                return receiver;
            }
        }
        return null;
    }

    private static boolean isCompatible(String donorBloodType, String receiverBloodType) {
        switch (receiverBloodType) {
            case "A+":
                return donorBloodType.equals("A+") || donorBloodType.equals("A-") ||
                       donorBloodType.equals("O+") || donorBloodType.equals("O-");
            case "A-":
                return donorBloodType.equals("A-") || donorBloodType.equals("O-");
            case "B+":
                return donorBloodType.equals("B+") || donorBloodType.equals("B-") ||
                       donorBloodType.equals("O+") || donorBloodType.equals("O-");
            case "B-":
                return donorBloodType.equals("B-") || donorBloodType.equals("O-");
            case "AB+":
                return true; // AB+ can receive from all blood types
            case "AB-":
                return donorBloodType.equals("AB-") || donorBloodType.equals("A-") ||
                       donorBloodType.equals("B-") || donorBloodType.equals("O-");
            case "O+":
                return donorBloodType.equals("O+") || donorBloodType.equals("O-");
            case "O-":
                return donorBloodType.equals("O-");
            default:
                return false;
        }
    }
