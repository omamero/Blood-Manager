import javax.swing.*;

public class BloodManager {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BloodManagementGUI gui = new BloodManagementGUI();
            gui.setVisible(true);
        });
    }
}