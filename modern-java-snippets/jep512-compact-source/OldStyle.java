import java.util.List;

/**
 * Before JEP 512, every Java program - no matter how small - needed a public
 * class declaration, a {@code public static void main(String[] args)}
 * signature, and explicit imports for anything outside {@code java.lang}.
 */
public class OldStyle {
    public static void main(String[] args) {
        List<String> groceries = List.of("Milk", "Eggs", "Bread");
        System.out.println("Shopping list:");
        for (String item : groceries) {
            System.out.println("- " + item);
        }
    }
}
