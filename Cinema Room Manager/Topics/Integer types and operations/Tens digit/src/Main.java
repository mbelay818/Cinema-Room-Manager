import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        final int ten = 10;
        int inputNumber = scanner.nextInt();
        int result = inputNumber / ten % ten;

        System.out.println(result);
    }
}