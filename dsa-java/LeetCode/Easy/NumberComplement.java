import java.util.Scanner;

public class NumberComplement {

    public static int findComplement(int num) {
        int mask = 0;
        int temp = num;

        while (temp > 0) {
            mask = (mask << 1) | 1;
            temp >>= 1;
        }

        return num ^ mask;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number: ");
        int num = sc.nextInt();

        int result = findComplement(num);

        System.out.println("Complement = " + result);

        sc.close();
    }
}