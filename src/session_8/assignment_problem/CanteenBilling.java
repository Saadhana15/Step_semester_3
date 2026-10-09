import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

abstract class Customer {
    protected final double billAmount;

    Customer(double billAmount) {
        this.billAmount = billAmount;
    }

    abstract String name();

    abstract double finalAmount();
}

class Student extends Customer {
    Student(double billAmount) {
        super(billAmount);
    }

    String name() {
        return "STUDENT";
    }

    double finalAmount() {
        return billAmount - billAmount * 0.10;
    }
}

class Staff extends Customer {
    Staff(double billAmount) {
        super(billAmount);
    }

    String name() {
        return "STAFF";
    }

    double finalAmount() {
        return billAmount - billAmount * 0.05;
    }
}

class Guest extends Customer {
    Guest(double billAmount) {
        super(billAmount);
    }

    String name() {
        return "GUEST";
    }

    double finalAmount() {
        return billAmount + 10;
    }
}

public class CanteenBilling {
    static Customer create(String type, double amount) {
        switch (type.toUpperCase()) {
            case "STUDENT":
                return new Student(amount);
            case "STAFF":
                return new Staff(amount);
            case "GUEST":
                return new Guest(amount);
            default:
                throw new IllegalArgumentException("Unknown customer type: " + type);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());

        List<Customer> bills = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = in.readLine().trim().split("\\s+");
            bills.add(create(parts[0], Double.parseDouble(parts[1])));
        }

        double total = 0;
        for (Customer c : bills) {
            double result = c.finalAmount();
            total += result;
            System.out.println(String.format(Locale.US, "%s: %.2f", c.name(), result));
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}