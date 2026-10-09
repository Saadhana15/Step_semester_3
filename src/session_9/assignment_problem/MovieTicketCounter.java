import java.util.*;

abstract class Ticket {
  
    protected static final double CONVENIENCE_FEE = 20;

    protected int count;
    Ticket(int count) { this.count = count; }

    abstract String seatName();
    abstract double ticketPrice();

    // Shared rule: every ticket carries the same fee.
    final double amount() { return count * (ticketPrice() + CONVENIENCE_FEE); }
}

class RegularTicket extends Ticket {
    RegularTicket(int count) { super(count); }
    String seatName() { return "REGULAR"; }
    double ticketPrice() { return 150; }
}

class PremiumTicket extends Ticket {
    PremiumTicket(int count) { super(count); }
    String seatName() { return "PREMIUM"; }
    double ticketPrice() { return 250; }
}

class ReclinerTicket extends Ticket {
    ReclinerTicket(int count) { super(count); }
    String seatName() { return "RECLINER"; }
    double ticketPrice() { return 400; }
}

public class MovieTicketCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String seat = sc.next();
            int count = sc.nextInt();
            Ticket t;
            switch (seat) {
                case "REGULAR":  t = new RegularTicket(count); break;
                case "PREMIUM":  t = new PremiumTicket(count); break;
                case "RECLINER": t = new ReclinerTicket(count); break;
                default: throw new IllegalArgumentException("Unknown seat: " + seat);
            }
            System.out.println(String.format(Locale.US, "%s: %.2f", t.seatName(), t.amount()));
            total += t.amount();
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}