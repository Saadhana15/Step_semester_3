import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

abstract class Journey {
    protected final double distance;

    Journey(double distance) {
        this.distance = distance;
    }

    abstract String name();

    abstract double fare();
}

class BusJourney extends Journey {
    BusJourney(double distance) {
        super(distance);
    }

    String name() {
        return "BUS";
    }

    double fare() {
        return Math.min(10, 2 + 0.10 * distance);
    }
}

class TrainJourney extends Journey {
    TrainJourney(double distance) {
        super(distance);
    }

    String name() {
        return "TRAIN";
    }

    double fare() {
        return 3 + 0.15 * distance;
    }
}

class MetroJourney extends Journey {
    private final double peakHourFactor;

    MetroJourney(double distance, double peakHourFactor) {
        super(distance);
        this.peakHourFactor = peakHourFactor;
    }

    String name() {
        return "METRO";
    }

    double fare() {
        return (1.50 + 0.20 * distance) * peakHourFactor;
    }
}

public class FareCalculator {
    static Journey create(String[] parts) {
        double distance = Double.parseDouble(parts[1]);
        switch (parts[0].toUpperCase()) {
            case "BUS":
                return new BusJourney(distance);
            case "TRAIN":
                return new TrainJourney(distance);
            case "METRO":
                return new MetroJourney(distance, Double.parseDouble(parts[2]));
            default:
                throw new IllegalArgumentException("Unknown transport type: " + parts[0]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());

        List<Journey> journeys = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            journeys.add(create(in.readLine().trim().split("\\s+")));
        }

        double total = 0;
        for (Journey j : journeys) {
            double fare = j.fare();
            total += fare;
            System.out.println(String.format(Locale.US, "%s: %.2f", j.name(), fare));
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}