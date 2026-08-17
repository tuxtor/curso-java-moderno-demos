import java.util.List;

public class OldStyle {
    public static void main(String[] args) {
        List<String> groceries = List.of("Milk", "Eggs", "Bread");
        System.out.println("Shopping list:");
        for (String item : groceries) {
            System.out.println("- " + item);
        }
    }
}
