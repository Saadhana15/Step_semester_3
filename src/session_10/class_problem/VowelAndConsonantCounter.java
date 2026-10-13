import java.util.Scanner;

public class VowelAndConsonantCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.next().toLowerCase();
        int vowels = 0, consonants = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels++;
            } else if (c >= 'a' && c <= 'z') {
                consonants++;
            }
        }
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
    }
}