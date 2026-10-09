import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class LibraryItem {
    protected final String title;

    LibraryItem(String title) {
        this.title = title;
    }

    abstract int loanDays();

    String getTitle() {
        return title;
    }

    LocalDate dueDate(LocalDate today) {
        return today.plusDays(loanDays());
    }
}

class Book extends LibraryItem {
    Book(String title) {
        super(title);
    }

    int loanDays() {
        return 14;
    }
}

class Dvd extends LibraryItem {
    Dvd(String title) {
        super(title);
    }

    int loanDays() {
        return 7;
    }
}

class Magazine extends LibraryItem {
    Magazine(String title) {
        super(title);
    }

    int loanDays() {
        return 3;
    }
}

public class LibraryDueDates {
    static LibraryItem create(String type, String title) {
        switch (type.toUpperCase()) {
            case "BOOK":
                return new Book(title);
            case "DVD":
                return new Dvd(title);
            case "MAGAZINE":
                return new Magazine(title);
            default:
                throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());
        LocalDate today = LocalDate.of(2023, 10, 26);

        List<LibraryItem> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = in.readLine().trim();
            int space = line.indexOf(' ');
            String type = line.substring(0, space);
            String title = line.substring(space + 1).trim();
            if (title.length() >= 2 && title.startsWith("\"") && title.endsWith("\"")) {
                title = title.substring(1, title.length() - 1);
            }
            items.add(create(type, title));
        }

        for (LibraryItem item : items) {
            System.out.println(item.getTitle() + ": " + item.dueDate(today));
        }
    }
}