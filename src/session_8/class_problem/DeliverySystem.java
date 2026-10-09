import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

abstract class Delivery {
    protected final double weight;
    protected final double distance;

    Delivery(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    abstract String name();

    abstract double fee();
}

class StandardDelivery extends Delivery {
    StandardDelivery(double weight, double distance) {
        super(weight, distance);
    }

    String name() {
        return "STANDARD";
    }

    double fee() {
        return 5 + 0.50 * weight + 0.10 * distance;
    }
}

class ExpressDelivery extends Delivery {
    ExpressDelivery(double weight, double distance) {
        super(weight, distance);
    }

    String name() {
        return "EXPRESS";
    }

    double fee() {
        return 15 + 1.00 * weight + 0.20 * distance;
    }
}

class InternationalDelivery extends Delivery {
    private final double customsFee;

    InternationalDelivery(double weight, double distance, double customsFee) {
        super(weight, distance);
        this.customsFee = customsFee;
    }

    String name() {
        return "INTERNATIONAL";
    }

    double fee() {
        return 25 + 2.00 * weight + 0.50 * distance + customsFee;
    }
}

public class DeliverySystem {
    static Delivery create(String[] parts) {
        double weight = Double.parseDouble(parts[1]);
        double distance = Double.parseDouble(parts[2]);
        switch (parts[0].toUpperCase()) {
            case "STANDARD":
                return new StandardDelivery(weight, distance);
            case "EXPRESS":
                return new ExpressDelivery(weight, distance);
            case "INTERNATIONAL":
                return new InternationalDelivery(weight, distance, Double.parseDouble(parts[3]));
            default:
                throw new IllegalArgumentException("Unknown delivery type: " + parts[0]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());

        List<Delivery> deliveries = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            deliveries.add(create(in.readLine().trim().split("\\s+")));
        }

        double total = 0;
        for (Delivery d : deliveries) {
            double fee = d.fee();
            total += fee;
            System.out.println(String.format(Locale.US, "%s: %.2f", d.name(), fee));
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}