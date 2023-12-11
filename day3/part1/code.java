package day3.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class code {
  public static void main(String[] args) {
    try {
      File myObj = new File("day3/part1/input.txt");
      Scanner lineReader = new Scanner(myObj);
      Scanner myReader = new Scanner(myObj);

      ArrayList<String> lines = new ArrayList<String>();
      
      int total = 0;
      int totalLineNum = 0;
      int lineNum = 1;

      // solve problem here
      // store data
      while (lineReader.hasNextLine()) {
        String line = lineReader.nextLine();
        lines.add(line);
        totalLineNum++;
      }

      lineReader.close();

      // process data
      for (int lineCount = 0; lineCount < lines.size(); lineCount++) {
        String line = lines.get(lineCount);

        boolean calculating = false;
        int start = -1;
        int end = -1;
        int currentValue = -1;

        for (int i = 0; i < line.length(); i++) {
          char currentChar = line.charAt(i);

          if (Character.isDigit(currentChar)) {
            if (!calculating) {
              // start getting value
              calculating = true;
              start = i;
              currentValue = Integer.parseInt(String.valueOf(currentChar));
            } else {
              currentValue = currentValue * 10 + Integer.parseInt(String.valueOf(currentChar));
            }
          } else {
            if (calculating) {
              end = i - 1;
              
              // check if adjacent to symbol
              total += checkAdjacent(lines, lineNum - 1, start, end, currentValue);

              // reset everything
              start = -1;
              end = -1;
              currentValue = -1;
              calculating = false;
            }
          }
        }

        if (calculating) {
          end = line.length() - 1;
          
          // check if adjacent to symbol
          total += checkAdjacent(lines, lineNum - 1, start, end, currentValue);

          // reset everything
          start = -1;
          end = -1;
          currentValue = -1;
          calculating = false;
        }

        lineNum++;
      }

      // output the sum
      System.out.println(total);
      
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private static int checkAdjacent(ArrayList<String> data, int line, int start, int end, int value) {
    int startRow = -1;
    int endRow = -1;
    int startColumn = -1;
    int endColumn = -1;

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
    
    if (start - 1 < 0) {
      startColumn = 0;
      endColumn = end + 1;
    } else if (end + 1 > data.get(line).length() - 1) {
      startColumn = start - 1;
      endColumn = end;
    } else {
      startColumn = start - 1;
      endColumn = end + 1;
    }

    for (int i = startRow; i <= endRow; i++) {
      for (int j = startColumn; j <= endColumn; j++) {
        char currentChar = data.get(i).charAt(j);

        if (!Character.isDigit(currentChar) && currentChar != '.') {
          return value;
        }
      }
    }

    return 0;
  }
}