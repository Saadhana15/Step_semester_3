import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

abstract class Vehicle {
    protected final int hours;

    Vehicle(int hours) {
        this.hours = hours;
    }

    abstract String name();

    abstract double charge();
}

class Bike extends Vehicle {
    Bike(int hours) {
        super(hours);
    }

    String name() {
        return "BIKE";
    }

    double charge() {
        return 10.0 * hours;
    }
}

class Car extends Vehicle {
    Car(int hours) {
        super(hours);
    }

    String name() {
        return "CAR";
    }

    double charge() {
        return 30.0 + 20.0 * (hours - 1);
    }
}

class Truck extends Vehicle {
    Truck(int hours) {
        super(hours);
    }

    String name() {
        return "TRUCK";
    }

    double charge() {
        return Math.max(100.0, 50.0 * hours);
    }
}

public class ParkingCharges {
    static Vehicle create(String type, int hours) {
        switch (type.toUpperCase()) {
            case "BIKE":
                return new Bike(hours);
            case "CAR":
                return new Car(hours);
            case "TRUCK":
                return new Truck(hours);
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());

        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = in.readLine().trim().split("\\s+");
            vehicles.add(create(parts[0], Integer.parseInt(parts[1])));
        }

        double total = 0;
        for (Vehicle v : vehicles) {
            double charge = v.charge();
            total += charge;
            System.out.println(String.format(Locale.US, "%s: %.2f", v.name(), charge));
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}