package day1.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Code {
  public static void main(String[] args) {
    try {
      File myObj = new File("day1/part1/input.txt");
      Scanner myReader = new Scanner(myObj);

      int total = 0;

      // solve problem here
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();

        // keep all the numbers
        String numbers = data.replaceAll("[^\\d.]", "");
        
        // get the first and last digit and turn into value
        int first = Integer.parseInt(String.valueOf(numbers.charAt(0)));
        int last = Integer.parseInt(String.valueOf(numbers.charAt(numbers.length() - 1)));
        int value = first * 10 + last;

        // sum it up
        total += value;
      }

      // output the sum
      System.out.println(total);
      
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}