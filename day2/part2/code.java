package day2.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Code {
  public static void main(String[] args) {
    try {
      File myObj = new File("day2/part2/input.txt");
      Scanner myReader = new Scanner(myObj);

      int total = 0;

      // solve problem here
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();

        // param: input, color
        int red = maxBallCount(data, "red");
        int green = maxBallCount(data, "green");
        int blue = maxBallCount(data, "blue");

        total += red * green * blue;
      }

      // output the sum
      System.out.println(total);
      
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private static int maxBallCount(String input, String color) {
    int max = Integer.MIN_VALUE;

    for (int index = input.indexOf(color); index >= 0; index = input.indexOf(color, index + 1)) {
      int numIndex = index - 2;
      int ballCount = 0, power = 0;

      while (Character.isDigit(input.charAt(numIndex))) {
        ballCount = (int) (ballCount + (Integer.parseInt(String.valueOf(input.charAt(numIndex))) * Math.pow(10, power)));
        numIndex--;
        power++;
      }

      if (ballCount > max) {
        max = ballCount;
      }
    }

    return max;
  }
}