import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // put your code here
    int h = scanner.nextInt();
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    int climbedPerDay = a - b;
    int daysTook = 1;

    for(int i = climbedPerDay; i < h; i+= climbedPerDay){
      daysTook++;
      if(i + a > h){
        System.out.println(daysTook);
        break;
      }
    }


  }
}
