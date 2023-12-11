package day4.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class code {
  public static void main(String[] args) {
    try {
      File myObj = new File("day4/part1/input.txt");
      Scanner myReader = new Scanner(myObj);

      // solve problem here
      int total = 0;

      // store data
      while (myReader.hasNextLine()) {
        String line = myReader.nextLine();

        String[] winningNumbers = line.split(" \\| ")[0].split(": ")[1].split(" ");
        ArrayList<String> winningNumberList = new ArrayList<String>(Arrays.asList(winningNumbers));
        winningNumberList.removeAll(Arrays.asList(""));

        String[] owningNumbers = line.split(" \\| ")[1].split(" ");
        ArrayList<String> owningNumberList = new ArrayList<String>(Arrays.asList(owningNumbers));
        owningNumberList.removeAll(Arrays.asList(""));

        owningNumberList.retainAll(winningNumberList);

        if (owningNumberList.size() > 0) {
          total += Math.pow(2, owningNumberList.size() - 1);
        }
      }

      System.out.println(total);
      
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}