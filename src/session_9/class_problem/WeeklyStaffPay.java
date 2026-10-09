import java.util.*;

abstract class Staff {
    protected String name;
    Staff(String name) { this.name = name; }
    String getName() { return name; }
    abstract double calculatePay();
}

class FullTimeStaff extends Staff {
    private double weeklySalary;
    FullTimeStaff(String name, double weeklySalary) { super(name); this.weeklySalary = weeklySalary; }
    double calculatePay() { return weeklySalary; }
}

class HourlyStaff extends Staff {
    private double hours, rate;
    HourlyStaff(String name, double hours, double rate) { super(name); this.hours = hours; this.rate = rate; }
    double calculatePay() {
        if (hours <= 40) return hours * rate;
        return 40 * rate + (hours - 40) * rate * 1.5;
    }
}

class Intern extends Staff {
    private double stipend;
    Intern(String name, double stipend) { super(name); this.stipend = stipend; }
    double calculatePay() { return stipend; }
}

public class WeeklyStaffPay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Staff> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            switch (type) {
                case "FULLTIME": list.add(new FullTimeStaff(name, sc.nextDouble())); break;
                case "HOURLY": {
                    double hours = sc.nextDouble();
                    double rate = sc.nextDouble();
                    list.add(new HourlyStaff(name, hours, rate));
                    break;
                }
                case "INTERN": list.add(new Intern(name, sc.nextDouble())); break;
                default: throw new IllegalArgumentException("Unknown type: " + type);
            }
        }
        double total = 0;
        for (Staff s : list) {
            double pay = s.calculatePay();
            System.out.println(String.format(Locale.US, "%s: %.2f", s.getName(), pay));
            total += pay;
        }
        System.out.println(String.format(Locale.US, "Total Payroll: %.2f", total));
    }
}