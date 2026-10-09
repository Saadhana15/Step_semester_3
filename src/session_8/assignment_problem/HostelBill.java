import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

abstract class Room {
    protected final int units;

    Room(int units) {
        this.units = units;
    }

    abstract String name();

    abstract double bill();
}

class SingleRoom extends Room {
    SingleRoom(int units) {
        super(units);
    }

    String name() {
        return "SINGLE";
    }

    double bill() {
        return 8.0 * units;
    }
}

class SharedRoom extends Room {
    private final int occupants;

    SharedRoom(int units, int occupants) {
        super(units);
        this.occupants = occupants;
    }

    String name() {
        return "SHARED";
    }

    double bill() {
        return 6.0 * units / occupants;
    }
}

class AcRoom extends Room {
    AcRoom(int units) {
        super(units);
    }

    String name() {
        return "AC";
    }

    double bill() {
        return 10.0 * units + 200.0;
    }
}

public class HostelBill {
    static Room create(String[] parts) {
        int units = Integer.parseInt(parts[1]);
        switch (parts[0].toUpperCase()) {
            case "SINGLE":
                return new SingleRoom(units);
            case "SHARED":
                return new SharedRoom(units, Integer.parseInt(parts[2]));
            case "AC":
                return new AcRoom(units);
            default:
                throw new IllegalArgumentException("Unknown room type: " + parts[0]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());

        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            rooms.add(create(in.readLine().trim().split("\\s+")));
        }

        double total = 0;
        for (Room r : rooms) {
            double bill = r.bill();
            total += bill;
            System.out.println(String.format(Locale.US, "%s: %.2f", r.name(), bill));
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}