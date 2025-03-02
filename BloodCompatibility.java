public class BloodCompatibility {
    public static boolean isCompatible(String donorBlood, String receiverBlood) {
        return switch (receiverBlood) {
            case "A+" -> donorBlood.matches("A[+\\-]|O[+\\-]");
            case "A-" -> donorBlood.matches("A-|O-");
            case "B+" -> donorBlood.matches("B[+\\-]|O[+\\-]");
            case "B-" -> donorBlood.matches("B-|O-");
            case "AB+" -> true;
            case "AB-" -> donorBlood.matches("AB-|A-|B-|O-");
            case "O+" -> donorBlood.matches("O[+\\-]");
            case "O-" -> donorBlood.equals("O-");
            default -> false;
        };
    }
}