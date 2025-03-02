import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BloodManagementGUI extends JFrame {
    // Data Storage
    private List<Donor> donors = new ArrayList<>();
    private List<Receiver> receivers = new ArrayList<>();
    private List<Donation> donations = new ArrayList<>();
    private List<BloodStock> bloodStocks = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    // GUI Components
    private JTabbedPane tabbedPane;

    public BloodManagementGUI() {
        setTitle("Blood Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize Tabs
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Admin", new AdminPanel());
        tabbedPane.addTab("Donor", new DonorPanel());
        tabbedPane.addTab("Receiver", new ReceiverPanel());

        add(tabbedPane);
    }

    // ==== Inner Panels ====
    class AdminPanel extends JPanel {
        public AdminPanel() {
            setLayout(new BorderLayout());
            
            // Buttons
            JButton btnViewDonations = new JButton("View All Donations");
            JButton btnViewStock = new JButton("View Blood Stock");
            
            // Output Area
            JTextArea outputArea = new JTextArea();
            outputArea.setEditable(false);

            // Button Actions
            btnViewDonations.addActionListener(e -> {
                outputArea.setText("");
                for (Donation d : donations) {
                    outputArea.append(d.toString() + "\n");
                }
            });

            btnViewStock.addActionListener(e -> {
                outputArea.setText("");
                for (BloodStock bs : bloodStocks) {
                    outputArea.append(bs.toString() + "\n");
                }
            });

            // Layout
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(btnViewDonations);
            buttonPanel.add(btnViewStock);
            
            add(buttonPanel, BorderLayout.NORTH);
            add(new JScrollPane(outputArea), BorderLayout.CENTER);
        }
    }

    class DonorPanel extends JPanel {
        private JTextField txtId, txtName, txtAge, txtContact, txtAddress, txtBloodType;

        public DonorPanel() {
            setLayout(new GridLayout(7, 2, 5, 5));
            
            // Form Components
            add(new JLabel("Donor ID:"));
            txtId = new JTextField();
            add(txtId);
            
            add(new JLabel("Name:"));
            txtName = new JTextField();
            add(txtName);
            
            add(new JLabel("Age:"));
            txtAge = new JTextField();
            add(txtAge);
            
            add(new JLabel("Contact:"));
            txtContact = new JTextField();
            add(txtContact);
            
            add(new JLabel("Address:"));
            txtAddress = new JTextField();
            add(txtAddress);
            
            add(new JLabel("Blood Type:"));
            txtBloodType = new JTextField();
            add(txtBloodType);
            
            JButton btnRegister = new JButton("Register & Donate");
            btnRegister.addActionListener(e -> handleDonorRegistration());
            add(btnRegister);
        }

        private void handleDonorRegistration() {
            try {
                // Validate Inputs
                int id = Integer.parseInt(txtId.getText());
                String name = txtName.getText();
                int age = Integer.parseInt(txtAge.getText());
                String contact = txtContact.getText();
                String address = txtAddress.getText();
                String bloodType = txtBloodType.getText().toUpperCase();

                // Create Donor
                Donor donor = new Donor(id, name, age, contact, address, bloodType);
                donors.add(donor);

                // Try to match with receiver
                Receiver match = findMatchingReceiver(bloodType);
                if (match != null) {
                    // Create Donation and Appointment
                    Donation donation = new Donation(
                        donations.size() + 1, 
                        new Date(), 
                        1, // Hospital ID
                        donor.getId(), 
                        match.getId(), 
                        1 // Quantity
                    );
                    donations.add(donation);
                    
                    Appointment appt = new Appointment(
                        appointments.size() + 1,
                        new Date(),
                        "Scheduled",
                        donor.getId(),
                        match.getId()
                    );
                    appointments.add(appt);
                    
                    JOptionPane.showMessageDialog(this, 
                        "Appointment Scheduled!\n" + appt.toString());
                } else {
                    // Add to Blood Stock
                    BloodStock stock = new BloodStock(1, bloodType, 1);
                    bloodStocks.add(stock);
                    JOptionPane.showMessageDialog(this, 
                        "No immediate match. Blood added to stock.");
                }

                // Clear Fields
                clearFields();

            } catch (NumberFormatException ex) {
                ExceptionHandler.handle(ex, "Invalid numeric input!");
            } catch (IllegalArgumentException ex) {
                ExceptionHandler.handle(ex, "Invalid blood type!");
            }
        }

        private void clearFields() {
            txtId.setText("");
            txtName.setText("");
            txtAge.setText("");
            txtContact.setText("");
            txtAddress.setText("");
            txtBloodType.setText("");
        }
    }

    class ReceiverPanel extends JPanel {
        private JTextField txtId, txtName, txtAge, txtContact, txtAddress, 
                          txtBloodType, txtCondition;

        public ReceiverPanel() {
            setLayout(new GridLayout(8, 2, 5, 5));
            
            // Form Components
            add(new JLabel("Receiver ID:"));
            txtId = new JTextField();
            add(txtId);
            
            add(new JLabel("Name:"));
            txtName = new JTextField();
            add(txtName);
            
            add(new JLabel("Age:"));
            txtAge = new JTextField();
            add(txtAge);
            
            add(new JLabel("Contact:"));
            txtContact = new JTextField();
            add(txtContact);
            
            add(new JLabel("Address:"));
            txtAddress = new JTextField();
            add(txtAddress);
            
            add(new JLabel("Blood Type:"));
            txtBloodType = new JTextField();
            add(txtBloodType);
            
            add(new JLabel("Medical Condition:"));
            txtCondition = new JTextField();
            add(txtCondition);
            
            JButton btnRequest = new JButton("Request Blood");
            btnRequest.addActionListener(e -> handleBloodRequest());
            add(btnRequest);
        }

        private void handleBloodRequest() {
            try {
                // Validate Inputs
                int id = Integer.parseInt(txtId.getText());
                String name = txtName.getText();
                int age = Integer.parseInt(txtAge.getText());
                String contact = txtContact.getText();
                String address = txtAddress.getText();
                String bloodType = txtBloodType.getText().toUpperCase();
                String condition = txtCondition.getText();

                // Create Receiver
                Receiver receiver = new Receiver(
                    id, name, age, contact, address, bloodType, condition
                );
                receivers.add(receiver);

                // Check Blood Stock
                BloodStock stock = findMatchingStock(bloodType);
                if (stock != null) {
                    JOptionPane.showMessageDialog(this, 
                        "Blood available! Contact donor: " + findDonorByBloodType(bloodType));
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "No matching blood in stock. Added to waiting list.");
                }

                // Clear Fields
                clearFields();

            } catch (NumberFormatException ex) {
                ExceptionHandler.handle(ex, "Invalid numeric input!");
            }
        }

        private void clearFields() {
            txtId.setText("");
            txtName.setText("");
            txtAge.setText("");
            txtContact.setText("");
            txtAddress.setText("");
            txtBloodType.setText("");
            txtCondition.setText("");
        }
    }

    // ==== Utility Methods ====
    private Receiver findMatchingReceiver(String bloodType) {
        for (Receiver r : receivers) {
            if (BloodCompatibility.isCompatible(bloodType, r.getBloodType())) {
                return r;
            }
        }
        return null;
    }

    private BloodStock findMatchingStock(String bloodType) {
        for (BloodStock bs : bloodStocks) {
            if (bs.getBloodType().equals(bloodType) && bs.getQuantity() > 0) {
                return bs;
            }
        }
        return null;
    }

    private Donor findDonorByBloodType(String bloodType) {
        for (Donor d : donors) {
            if (d.getBloodType().equals(bloodType)) {
                return d;
            }
        }
        return null;
    }
}