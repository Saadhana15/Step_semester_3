import java.util.*;

abstract class LibraryItem {
    protected String title;
    protected int daysLate;
    LibraryItem(String title, int daysLate) { this.title = title; this.daysLate = daysLate; }
    String getTitle() { return title; }
    abstract double calculateFine();
}

class Book extends LibraryItem {
    Book(String t, int d) { super(t, d); }
    double calculateFine() { return 2.0 * daysLate; }
}

class DVD extends LibraryItem {
    DVD(String t, int d) { super(t, d); }
    double calculateFine() { return Math.min(5.0 * daysLate, 50.0); }
}

class Magazine extends LibraryItem {
    Magazine(String t, int d) { super(t, d); }
    double calculateFine() { return 1.0 * daysLate; }
}

public class LibraryLateFineCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<LibraryItem> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String title = sc.next();
            int days = sc.nextInt();
            switch (type) {
                case "BOOK": items.add(new Book(title, days)); break;
                case "DVD": items.add(new DVD(title, days)); break;
                case "MAGAZINE": items.add(new Magazine(title, days)); break;
                default: throw new IllegalArgumentException("Unknown type: " + type);
            }
        }
        double total = 0;
        for (LibraryItem it : items) {
            double fine = it.calculateFine();
            System.out.println(String.format(Locale.US, "%s: %.2f", it.getTitle(), fine));
            total += fine;
        }
        System.out.println(String.format(Locale.US, "Total Fines: %.2f", total));
    }
}