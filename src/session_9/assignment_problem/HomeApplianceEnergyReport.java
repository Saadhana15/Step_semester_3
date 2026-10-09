import java.util.*;

interface SaverMode {
    double SAVER_REDUCTION = 0.25;
    default double applySaver(double units) { return units * (1 - SAVER_REDUCTION); }
}

abstract class Appliance {
    protected static final double COST_PER_UNIT = 8;

    protected double hours;
    Appliance(double hours) { this.hours = hours; }

    abstract String applianceName();
    abstract double powerWatts();

    double calculateUnits() { return powerWatts() * hours / 1000; }
    static double costOf(double units) { return units * COST_PER_UNIT; }
}

class Fridge extends Appliance {
    Fridge(double h) { super(h); }
    String applianceName() { return "FRIDGE"; }
    double powerWatts() { return 150; }
}

class AirConditioner extends Appliance implements SaverMode {
    AirConditioner(double h) { super(h); }
    String applianceName() { return "AC"; }
    double powerWatts() { return 1500; }
}

class Television extends Appliance {
    Television(double h) { super(h); }
    String applianceName() { return "TV"; }
    double powerWatts() { return 100; }
}

class WashingMachine extends Appliance implements SaverMode {
    WashingMachine(double h) { super(h); }
    String applianceName() { return "WASHER"; }
    double powerWatts() { return 500; }
}

public class HomeApplianceEnergyReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().trim().split("\\s+");
            String type = parts[0];
            double hours = Double.parseDouble(parts[1]);
            boolean saver = parts.length > 2 && parts[2].equals("SAVER");

            Appliance a;
            switch (type) {
                case "FRIDGE": a = new Fridge(hours); break;
                case "AC":     a = new AirConditioner(hours); break;
                case "TV":     a = new Television(hours); break;
                case "WASHER": a = new WashingMachine(hours); break;
                default: throw new IllegalArgumentException("Unknown appliance: " + type);
            }

            double units = a.calculateUnits();
            if (saver) {
                if (a instanceof SaverMode) {
                    units = ((SaverMode) a).applySaver(units);
                } else {
                    System.out.println(a.applianceName() + ": saver mode not supported");
                    continue;
                }
            }
            double cost = Appliance.costOf(units);
            System.out.println(String.format(Locale.US, "%s: Units=%.2f Cost=%.2f", a.applianceName(), units, cost));
            total += cost;
        }
        System.out.println(String.format(Locale.US, "Total Cost: %.2f", total));
    }
}