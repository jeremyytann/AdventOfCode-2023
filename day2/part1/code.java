package day2.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Code {
  public static void main(String[] args) {
    try {
      File myObj = new File("day2/part1/input.txt");
      Scanner myReader = new Scanner(myObj);

      int game = 1;
      int total = 0;

      // solve problem here
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();

        // param: input, color, maxCount
        boolean red = validBallCount(data, "red", 12);
        boolean green = validBallCount(data, "green", 13);
        boolean blue = validBallCount(data, "blue", 14);

        total += (red && green && blue) ? game : 0;
        game++;
      }

      // output the sum
      System.out.println(total);
      
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private static boolean validBallCount(String input, String color, int maxCount) {
    for (int index = input.indexOf(color); index >= 0; index = input.indexOf(color, index + 1)) {
      int numIndex = index - 2;
      int ballCount = 0, power = 0;

      while (Character.isDigit(input.charAt(numIndex))) {
        ballCount = (int) (ballCount + (Integer.parseInt(String.valueOf(input.charAt(numIndex))) * Math.pow(10, power)));
        numIndex--;
        power++;
      }

      if (ballCount > maxCount) {
        return false;
      }
    }

    return true;
  }
}