import javax.swing.JOptionPane;

public class ExceptionHandler {
    public static void handle(Exception ex, String message) {
        JOptionPane.showMessageDialog(null,
            "Error: " + message + "\nDetails: " + ex.getMessage(),
            "System Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}