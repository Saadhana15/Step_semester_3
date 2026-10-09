import java.util.*;

abstract class Plot {
    protected String owner;
    Plot(String owner) { this.owner = owner; }
    String getOwner() { return owner; }
    abstract String shapeName();
    abstract double area();
}

class CirclePlot extends Plot {
    private double radius;
    CirclePlot(String owner, double radius) { super(owner); this.radius = radius; }
    String shapeName() { return "CIRCLE"; }
    double area() { return Math.PI * radius * radius; }
}

class RectanglePlot extends Plot {
    private double length, width;
    RectanglePlot(String owner, double length, double width) {
        super(owner); this.length = length; this.width = width;
    }
    String shapeName() { return "RECTANGLE"; }
    double area() { return length * width; }
}

class TrianglePlot extends Plot {
    private double base, height;
    TrianglePlot(String owner, double base, double height) {
        super(owner); this.base = base; this.height = height;
    }
    String shapeName() { return "TRIANGLE"; }
    double area() { return 0.5 * base * height; }
}

public class GardenPlotAreaReport {
    // Only place that knows about concrete shapes (creation).
    static Plot create(String shape, String owner, Scanner sc) {
        switch (shape) {
            case "CIRCLE":    return new CirclePlot(owner, sc.nextDouble());
            case "RECTANGLE": return new RectanglePlot(owner, sc.nextDouble(), sc.nextDouble());
            case "TRIANGLE":  return new TrianglePlot(owner, sc.nextDouble(), sc.nextDouble());
            default: throw new IllegalArgumentException("Unknown shape: " + shape);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Plot> plots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String shape = sc.next();
            String owner = sc.next();
            plots.add(create(shape, owner, sc));
        }
        // Report code: works only with Plot, never changes when a new shape is added.
        double total = 0;
        for (Plot p : plots) {
            System.out.println(String.format(Locale.US, "%s (%s): %.2f", p.getOwner(), p.shapeName(), p.area()));
            total += p.area();
        }
        System.out.println(String.format(Locale.US, "Total Area: %.2f", total));
    }
}