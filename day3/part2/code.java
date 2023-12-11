package day3.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class code {
  public static void main(String[] args) {
    try {
      File myObj = new File("day3/part2/input.txt");
      Scanner lineReader = new Scanner(myObj);
      Scanner myReader = new Scanner(myObj);

      ArrayList<String> lines = new ArrayList<String>();
      
      int total = 0;
      int lineNum = 1;

      // solve problem here
      // store data
      while (lineReader.hasNextLine()) {
        String line = lineReader.nextLine();
        lines.add(line);
      }

      lineReader.close();

      // process data
      for (int lineCount = 0; lineCount < lines.size(); lineCount++) {
        String line = lines.get(lineCount);

        for (int i = 0; i < line.length(); i++) {
          char currentChar = line.charAt(i);

          if (currentChar == '*') {
            // check gear ratio within 3*3 of the '*'
            total += checkGearRatio(lines, lineCount, i);
          }
        }
      }

      // output the sum
      System.out.println(total);
      
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private static int checkGearRatio(ArrayList<String> data, int line, int pos) {
    int startRow = -1;
    int endRow = -1;
    int startColumn = -1;
    int endColumn = -1;

    ArrayList<Integer> gears = new ArrayList<Integer>();
    int gearCount = 0;

    if (line - 1 < 0) {
      startRow = 0;
      endRow = line + 1;
    } else if (line + 1 > data.size() - 1) {
      startRow = line - 1;
      endRow = line;
    } else {
      startRow = line - 1;
      endRow = line + 1;
    }
    
    if (pos - 1 < 0) {
      startColumn = 0;
      endColumn = pos + 1;
    } else if (pos + 1 > data.get(line).length() - 1) {
      startColumn = pos - 1;
      endColumn = pos;
    } else {
      startColumn = pos - 1;
      endColumn = pos + 1;
    }
    
    for (int i = startRow; i <= endRow; i++) {
      boolean calculating = false;

      for (int j = startColumn; j <= endColumn; j++) {
        char currentChar = data.get(i).charAt(j);

        if (Character.isDigit(currentChar)) {
          if (!calculating) {
            calculating = true;
          }
        } else {
          if (calculating) {
            // get the value of number if number character chain ended
            int value = calculateValue(data, i, j - 1);
            gears.add(value);
            gearCount++;
            
            calculating = false;
          }
        }
      }

      if (calculating) {
        // get the value of number if number character chain ended
        int value = calculateValue(data, i, endColumn);
        gears.add(value);
        gearCount++;

        calculating = false;
      }
    }

    // add the multiplication into sum if gear count is 2
    if (gearCount == 2) {
      return gears.get(0) * gears.get(1); 
    }
    
    return 0;
  }

  private static int calculateValue(ArrayList<String> data, int line, int index) {
    String string = data.get(line);
    int start = 0;
    int value = 0;

    for (int i = index; i >= 0; i--) {
      if (!Character.isDigit(string.charAt(i))) {
        start = i + 1;
        break;
      }
    }

    for (int i = start; i <= string.length() - 1; i++) {
      if (Character.isDigit(string.charAt(i))) {
        value = value * 10 + Integer.parseInt(String.valueOf(string.charAt(i)));
      } else {
        break;
      }
    }

    return value;
  }
}