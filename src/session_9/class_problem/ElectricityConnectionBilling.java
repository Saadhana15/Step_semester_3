import java.util.*;

abstract class Connection {
    protected double units;
    Connection(double units) { this.units = units; }
    abstract String typeName();
    abstract double calculateBill();
}

class HomeConnection extends Connection {
    HomeConnection(double u) { super(u); }
    String typeName() { return "HOME"; }
    double calculateBill() {
        if (units <= 100) return units * 5;
        return 100 * 5 + (units - 100) * 7;
    }
}

class ShopConnection extends Connection {
    ShopConnection(double u) { super(u); }
    String typeName() { return "SHOP"; }
    double calculateBill() { return units * 8 + 100; }
}

class FactoryConnection extends Connection {
    FactoryConnection(double u) { super(u); }
    String typeName() { return "FACTORY"; }
    double calculateBill() { return Math.max(units * 6, 1000); }
}

public class ElectricityConnectionBilling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Connection> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double units = sc.nextDouble();
            switch (type) {
                case "HOME": list.add(new HomeConnection(units)); break;
                case "SHOP": list.add(new ShopConnection(units)); break;
                case "FACTORY": list.add(new FactoryConnection(units)); break;
                default: throw new IllegalArgumentException("Unknown type: " + type);
            }
        }
        double total = 0;
        for (Connection c : list) {
            double bill = c.calculateBill();
            System.out.println(String.format(Locale.US, "%s: %.2f", c.typeName(), bill));
            total += bill;
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}