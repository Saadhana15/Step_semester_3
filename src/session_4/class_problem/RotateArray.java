import java.util.Scanner;

public class RotateArray {

    static int[] rotateArray(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        int[] newArray = new int[n];

        for (int i = 0; i < n; i++) {
            newArray[(i + k) % n] = nums[i];
        }

        return newArray;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        int[] rotated = rotateArray(nums, k);

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < rotated.length; i++) {
            sb.append(rotated[i]);
            if (i < rotated.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        System.out.println(sb);

        sc.close();
    }
}