package day1.part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Code {
  public static void main(String[] args) {
    try {
      File myObj = new File("day1/part2/input.txt");
      Scanner myReader = new Scanner(myObj);

      int total = 0;

      // solve problem here
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        
        int firstNumIndex = getFirstNumIndex(data);
        int firstTextIndex = getFirstTextIndex(data);
        int first = getFirstDigit(data, Math.min(firstNumIndex, firstTextIndex));

        int lastNumIndex = getLastNumIndex(data);
        int lastTextIndex = getLastTextIndex(data);
        int last = getLastDigit(data, Math.max(lastNumIndex, lastTextIndex));

        int value = first * 10 + last;

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

  private static int getFirstNumIndex(String input) {
    int minIndex = Integer.MAX_VALUE;

    for (int i = 1; i < 10; i++) {
      if (input.indexOf(String.valueOf(i)) != -1 && input.indexOf(String.valueOf(i)) < minIndex) {
        minIndex = input.indexOf(String.valueOf(i));
      }
    }

    return minIndex;
  }

  private static int getFirstTextIndex(String input) {
    int minIndex = Integer.MAX_VALUE;
    String[] numbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    for (int i = 0; i < 9; i++) {
      if (input.indexOf(numbers[i]) != -1 && input.indexOf(numbers[i]) < minIndex) {
        minIndex = input.indexOf(numbers[i]);
      }
    }

    return minIndex;
  }

  private static int getFirstDigit(String input, int index) {
    int digit = -1;
    String[] numbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    if (Character.isDigit(input.charAt(index))) {
      digit = Integer.parseInt(String.valueOf(input.charAt(index)));
    } else {
      for (int i = 0; i < 9; i++) {
        if (input.indexOf(numbers[i]) == index) {
          digit = i + 1;
          break;
        }
      }
    }

    return digit;
  }

  private static int getLastNumIndex(String input) {
    int maxIndex = Integer.MIN_VALUE;

    for (int i = 1; i < 10; i++) {
      if (input.lastIndexOf(String.valueOf(i)) != -1 && input.lastIndexOf(String.valueOf(i)) > maxIndex) {
        maxIndex = input.lastIndexOf(String.valueOf(i));
      }
    }

    return maxIndex;
  }

  private static int getLastTextIndex(String input) {
    int maxIndex = Integer.MIN_VALUE;
    String[] numbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    for (int i = 0; i < 9; i++) {
      if (input.lastIndexOf(numbers[i]) != -1 && input.lastIndexOf(numbers[i]) > maxIndex) {
        maxIndex = input.lastIndexOf(numbers[i]);
      }
    }

    return maxIndex;
  }

  private static int getLastDigit(String input, int index) {
    int digit = -1;
    String[] numbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    if (Character.isDigit(input.charAt(index))) {
      digit = Integer.parseInt(String.valueOf(input.charAt(index)));
    } else {
      for (int i = 0; i < 9; i++) {
        if (input.lastIndexOf(numbers[i]) == index) {
          digit = i + 1;
          break;
        }
      }
    }

    return digit;
  }
}