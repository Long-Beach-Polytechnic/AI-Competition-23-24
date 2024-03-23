import java.util.Scanner;

public class Util{

  private static Scanner scan = new Scanner(System.in);

  public static void clearConsole()
  {
    System.out.println("\033[H\033[2J");
    System.out.flush();
  }

  public static void pauseConsole()
  {
    System.out.println("\nPress enter to continue");
    scan.nextLine();
  }

  public static int enterInt(int min, int max)
  {
    int input;
    while (true) {
        System.out.print("Enter number: ");
      try {
        input = Integer.parseInt(scan.nextLine());
        if (input >= min && input <= max) {
          break;
        }
      } catch (NumberFormatException e) {
          //do nothing
        }
      System.out.println("Invalid input, enter again");
    }
    return input;
  }

}