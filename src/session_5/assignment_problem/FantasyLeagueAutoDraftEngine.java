import java.util.Arrays;
import java.util.Scanner;

class Player implements Comparable<Player> {
    String name;
    int matchesPlayed;
    double battingAverage;
    boolean injured;

    public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

    double fantasyPoints() {
        return battingAverage;
    }

    @Override
    public int compareTo(Player other) {
        return Double.compare(other.fantasyPoints(), this.fantasyPoints());
    }
}

public class FantasyLeagueAutoDraftEngine {

    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return !injured && matchesPlayed >= 5;
    }

    static String draftAndRank(Player[] players) {
        Player[] temp = new Player[players.length];
        int count = 0;

        for (Player p : players) {
            if (isDraftable(p.matchesPlayed) || isDraftable(p.matchesPlayed, p.injured)) {
                temp[count++] = p;
            }
        }

        Player[] draftable = Arrays.copyOf(temp, count);
        Arrays.sort(draftable);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < draftable.length; i++) {
            sb.append(i + 1).append(". ").append(draftable[i].name);
            if (i != draftable.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        Player[] players = new Player[n];

        for (int i = 0; i < n; i++) {
            String name = sc.nextLine();
            int matchesPlayed = Integer.parseInt(sc.nextLine().trim());
            double battingAverage = Double.parseDouble(sc.nextLine().trim());
            boolean injured = Boolean.parseBoolean(sc.nextLine().trim());
            players[i] = new Player(name, matchesPlayed, battingAverage, injured);
        }

        System.out.println(draftAndRank(players));

        sc.close();
    }
}