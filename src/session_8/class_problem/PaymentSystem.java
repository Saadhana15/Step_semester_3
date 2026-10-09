import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

abstract class Payment {
    protected final double amount;

    Payment(double amount) {
        this.amount = amount;
    }

    abstract String name();

    abstract double finalAmount();
}

class CardPayment extends Payment {
    CardPayment(double amount) {
        super(amount);
    }

    String name() {
        return "CARD";
    }

    double finalAmount() {
        return amount + amount * 0.02;
    }
}

class WalletPayment extends Payment {
    WalletPayment(double amount) {
        super(amount);
    }

    String name() {
        return "WALLET";
    }

    double finalAmount() {
        return amount + amount * 0.01;
    }
}

class BankTransferPayment extends Payment {
    BankTransferPayment(double amount) {
        super(amount);
    }

    String name() {
        return "BANKTRANSFER";
    }

    double finalAmount() {
        return amount;
    }
}

public class PaymentSystem {
    static Payment create(String type, double amount) {
        switch (type.toUpperCase()) {
            case "CARD":
                return new CardPayment(amount);
            case "WALLET":
                return new WalletPayment(amount);
            case "BANKTRANSFER":
                return new BankTransferPayment(amount);
            default:
                throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());

        List<Payment> payments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = in.readLine().trim().split("\\s+");
            payments.add(create(parts[0], Double.parseDouble(parts[1])));
        }

        double total = 0;
        for (Payment p : payments) {
            double result = p.finalAmount();
            total += result;
            System.out.println(String.format(Locale.US, "%s: %.2f", p.name(), result));
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}