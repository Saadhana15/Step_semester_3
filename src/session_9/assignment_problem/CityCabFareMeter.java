import java.util.*;

interface NightService {
    double NIGHT_SURCHARGE = 0.20;
    default double applyNightSurcharge(double fare) { return fare * (1 + NIGHT_SURCHARGE); }
}

abstract class Cab {
    protected static final double MINIMUM_FARE = 100;

    abstract String cabName();
    abstract double ratePerKm();

    final double calculateFare(double km) { return Math.max(km * ratePerKm(), MINIMUM_FARE); }
}

class MiniCab extends Cab {
    String cabName() { return "MINI"; }
    double ratePerKm() { return 10; }
}

class SedanCab extends Cab implements NightService {
    String cabName() { return "SEDAN"; }
    double ratePerKm() { return 14; }
}

class SuvCab extends Cab implements NightService {
    String cabName() { return "SUV"; }
    double ratePerKm() { return 18; }
}

public class CityCabFareMeter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double km = sc.nextDouble();
            String time = sc.next();
            Cab cab;
            switch (type) {
                case "MINI":  cab = new MiniCab(); break;
                case "SEDAN": cab = new SedanCab(); break;
                case "SUV":   cab = new SuvCab(); break;
                default: throw new IllegalArgumentException("Unknown cab: " + type);
            }
            double fare = cab.calculateFare(km);
            if (time.equals("NIGHT")) {
                if (cab instanceof NightService) {
                    fare = ((NightService) cab).applyNightSurcharge(fare);
                } else {
                    System.out.println(cab.cabName() + ": night service not available");
                    continue;
                }
            }
            System.out.println(String.format(Locale.US, "%s: %.2f", cab.cabName(), fare));
            total += fare;
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}