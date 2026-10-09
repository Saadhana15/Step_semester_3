import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

abstract class Employee {
    protected final String name;
    protected final double monthlySalary;

    Employee(String name, double monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    String getName() {
        return name;
    }

    abstract double bonus();
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    double bonus() {
        return monthlySalary * 0.10;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    double bonus() {
        return monthlySalary * 0.05;
    }
}

class InternEmployee extends Employee {
    InternEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    double bonus() {
        return 2000.0;
    }
}

public class BonusCalculator {
    static Employee create(String type, String name, double salary) {
        switch (type.toUpperCase()) {
            case "FULLTIME":
                return new FullTimeEmployee(name, salary);
            case "PARTTIME":
                return new PartTimeEmployee(name, salary);
            case "INTERN":
                return new InternEmployee(name, salary);
            default:
                throw new IllegalArgumentException("Unknown employee type: " + type);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());

        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = in.readLine().trim().split("\\s+");
            employees.add(create(parts[0], parts[1], Double.parseDouble(parts[2])));
        }

        double total = 0;
        for (Employee e : employees) {
            double bonus = e.bonus();
            total += bonus;
            System.out.println(String.format(Locale.US, "%s: %.2f", e.getName(), bonus));
        }
        System.out.println(String.format(Locale.US, "Total Bonus: %.2f", total));
    }
}