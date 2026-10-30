
void main() {
  List<String> groceries = List.of("Milk", "Eggs", "Bread");
  IO.println("Shopping list:");
  for (String item : groceries) {
    IO.println("- " + item);
  }
}
