// JEP 512: Compact Source Files and Instance Main Methods (finalized in
// JDK 25). No class declaration, no `public`/`static`, and no import needed
// for `List` - public top-level types from java.base are auto-imported in
// compact source files. Console output uses the new `IO.println`, part of
// `java.lang.IO`, instead of `System.out.println`.
//
// Run directly with the source-launcher, no compile step needed:
//   java NewStyle.java
void main() {
    var groceries = List.of("Milk", "Eggs", "Bread");
    IO.println("Shopping list:");
    for (var item : groceries) {
        IO.println("- " + item);
    }
}
