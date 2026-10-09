import java.util.*;


interface Insurable {
    double INSURANCE_RATE = 0.02;
    double getDeclaredValue();
    default double calculateInsurance() { return getDeclaredValue() * INSURANCE_RATE; }
}


abstract class Parcel {
    protected double weightKg;
    protected double declaredValue;

    Parcel(double weightKg, double declaredValue) {
        this.weightKg = weightKg;
        this.declaredValue = declaredValue;
    }

    public double getDeclaredValue() { return declaredValue; }
    abstract String typeName();
    abstract double calculateCharge();
}

class StandardParcel extends Parcel {
    StandardParcel(double w, double v) { super(w, v); }
    String typeName() { return "STANDARD"; }
    double calculateCharge() { return 40 + 10 * weightKg; }
}

class ExpressParcel extends Parcel implements Insurable {
    ExpressParcel(double w, double v) { super(w, v); }
    String typeName() { return "EXPRESS"; }
    double calculateCharge() { return 80 + 15 * weightKg; }
}

class FragileParcel extends StandardParcel implements Insurable {
    private static final double HANDLING_FEE = 50;
    FragileParcel(double w, double v) { super(w, v); }
    @Override String typeName() { return "FRAGILE"; }
    @Override double calculateCharge() { return super.calculateCharge() + HANDLING_FEE; }
}

public class ParcelShippingDesk {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double grand = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double w = sc.nextDouble();
            double v = sc.nextDouble();
            Parcel p;
            switch (type) {
                case "STANDARD": p = new StandardParcel(w, v); break;
                case "EXPRESS":  p = new ExpressParcel(w, v); break;
                case "FRAGILE":  p = new FragileParcel(w, v); break;
                default: throw new IllegalArgumentException("Unknown type: " + type);
            }
            double charge = p.calculateCharge();
            double insurance = (p instanceof Insurable) ? ((Insurable) p).calculateInsurance() : 0;
            double total = charge + insurance;
            System.out.println(String.format(Locale.US, "%s: Charge=%.2f Insurance=%.2f Total=%.2f",
                    p.typeName(), charge, insurance, total));
            grand += total;
        }
        System.out.println(String.format(Locale.US, "Grand Total: %.2f", grand));
    }
}