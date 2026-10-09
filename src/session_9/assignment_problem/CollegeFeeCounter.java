import java.util.*;


interface BusUser {
    double TRANSPORT_FEE = 12000;   
    default double transportFee() { return TRANSPORT_FEE; }
}

abstract class Student {
    protected String name;
    Student(String name) { this.name = name; }
    String getName() { return name; }

    abstract double tuition();
    double extraCharges() { return 0; }  

    final double totalFee() {
        double fee = tuition() + extraCharges();
        if (this instanceof BusUser) {
            fee += ((BusUser) this).transportFee();
        }
        return fee;
    }
}

class DayScholar extends Student implements BusUser {
    DayScholar(String name) { super(name); }
    double tuition() { return 40000; }
}

class Hosteller extends Student {
    Hosteller(String name) { super(name); }
    double tuition() { return 40000; }
    @Override double extraCharges() { return 60000; }   // hostel fee
}

class ScholarshipStudent extends Student implements BusUser {
    ScholarshipStudent(String name) { super(name); }
    double tuition() { return 40000 / 2; }
}

public class CollegeFeeCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            Student s;
            switch (type) {
                case "DAY_SCHOLAR": s = new DayScholar(name); break;
                case "HOSTELLER":   s = new Hosteller(name); break;
                case "SCHOLAR":     s = new ScholarshipStudent(name); break;
                default: throw new IllegalArgumentException("Unknown type: " + type);
            }
            System.out.println(String.format(Locale.US, "%s: %.2f", s.getName(), s.totalFee()));
            total += s.totalFee();
        }
        System.out.println(String.format(Locale.US, "Total Collected: %.2f", total));
    }
}