import java.util.*;

abstract class Booking {
    // Change the booking fee here ONLY.
    protected static final double BOOKING_FEE = 50;

    protected double distanceKm;
    Booking(double distanceKm) { this.distanceKm = distanceKm; }

    abstract String modeName();
    abstract double baseFare();

    // Template method: common fee added in one place.
    final double totalFare() { return baseFare() + BOOKING_FEE; }
}

class BusBooking extends Booking {
    BusBooking(double d) { super(d); }
    String modeName() { return "BUS"; }
    double baseFare() { return 2 * distanceKm; }
}

class TrainBooking extends Booking {
    TrainBooking(double d) { super(d); }
    String modeName() { return "TRAIN"; }
    double baseFare() { return 1.5 * distanceKm; }
}

class FlightBooking extends Booking {
    FlightBooking(double d) { super(d); }
    String modeName() { return "FLIGHT"; }
    double baseFare() { return 2500 + 4 * distanceKm; }
}

public class TravelBookingWithCommonFee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Booking> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String mode = sc.next();
            double d = sc.nextDouble();
            switch (mode) {
                case "BUS": list.add(new BusBooking(d)); break;
                case "TRAIN": list.add(new TrainBooking(d)); break;
                case "FLIGHT": list.add(new FlightBooking(d)); break;
                default: throw new IllegalArgumentException("Unknown mode: " + mode);
            }
        }
        for (Booking b : list)
            System.out.println(String.format(Locale.US, "%s: %.2f", b.modeName(), b.totalFare()));
    }
}