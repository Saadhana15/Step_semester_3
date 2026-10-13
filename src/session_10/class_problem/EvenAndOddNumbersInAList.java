import java.util.Arrays;
import java.util.List;

public class EvenAndOddNumbersInAList {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 8, 12, 5, 7, 10);
        int even = 0, odd = 0;
        for (int n : numbers) {
            if (n % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        System.out.println("Even: " + even);
        System.out.println("Odd: " + odd);
    }
}