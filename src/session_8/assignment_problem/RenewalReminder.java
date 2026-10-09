import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class Plan {
    protected final String name;
    protected final LocalDate startDate;

    Plan(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    String getName() {
        return name;
    }

    abstract int validityDays();

    LocalDate renewalDate() {
        return startDate.plusDays(validityDays());
    }
}

class BasicPlan extends Plan {
    BasicPlan(String name, LocalDate startDate) {
        super(name, startDate);
    }

    int validityDays() {
        return 30;
    }
}

class StandardPlan extends Plan {
    StandardPlan(String name, LocalDate startDate) {
        super(name, startDate);
    }

    int validityDays() {
        return 90;
    }
}

class PremiumPlan extends Plan {
    PremiumPlan(String name, LocalDate startDate) {
        super(name, startDate);
    }

    int validityDays() {
        return 365;
    }
}

public class RenewalReminder {
    static Plan create(String type, String name, LocalDate start) {
        switch (type.toUpperCase()) {
            case "BASIC":
                return new BasicPlan(name, start);
            case "STANDARD":
                return new StandardPlan(name, start);
            case "PREMIUM":
                return new PremiumPlan(name, start);
            default:
                throw new IllegalArgumentException("Unknown plan type: " + type);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());

        List<Plan> plans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = in.readLine().trim().split("\\s+");
            plans.add(create(parts[0], parts[1], LocalDate.parse(parts[2])));
        }

        for (Plan p : plans) {
            System.out.println(p.getName() + ": " + p.renewalDate());
        }
    }
}