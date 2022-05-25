package evrentan.examples;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Java 17 Crack Digit Code program.
 * First, you need to enter your code's digit number.
 * Then, you need to enter how many tries you have.
 * Then, enter your number and see what happens.
 *
 * @author <a href="https://github.com/evrentan">Evren Tan</a>
 */

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the code's digit number that will be generated and that you are going to find: ");
        int digitNumber = scanner.nextInt();
        System.out.print("Enter the number of allowed try number for the user: ");
        int allowedTryNumber = scanner.nextInt();

        int[] generatedCode = Integer.toString(new Random().nextInt((int) Math.pow(10D, digitNumber - 1), (int) Math.pow(10D, digitNumber) - 1)).chars().map(c -> c-'0').toArray();
        int generatedCodeLength = generatedCode.length;

        // remove this line before starting to play !!!
        System.out.println(Arrays.toString(Arrays.stream(generatedCode).toArray()));

        boolean findGeneratedCode = false;

        for (int i = 1; i <= allowedTryNumber; ) {
            String enteredNumber = scanner.next();

            if (enteredNumber.length() != generatedCodeLength) {
                System.out.println(String.format("You need to enter a code with %d digits.  You have still %d tries.", generatedCodeLength, allowedTryNumber - i + 1));
                continue;
            }

            if (enteredNumber.toCharArray()[0] == '0') {
                System.out.println(String.format("First digit should not be 0. You have still %d tries.", allowedTryNumber - i + 1));
                continue;
            }

            int[] enteredNumberList = enteredNumber.chars().map(x -> x - '0').toArray();

            if (Arrays.equals(generatedCode, enteredNumberList)) {
                findGeneratedCode = true;
                break;
            }

            int correctNumberCorrectPlace = 0;
            int correctNumberWrongPlace = 0;

            for (int j = 0; j < generatedCodeLength; j++) {
                if (enteredNumberList[j] == generatedCode[j]) {
                    correctNumberCorrectPlace++;
                    continue;
                }
                int finalJ = j;

                if (Arrays.stream(generatedCode).anyMatch(a -> a == enteredNumberList[finalJ]))
                    correctNumberWrongPlace++;


                if (Arrays.stream(generatedCode).noneMatch(a -> a == enteredNumberList[finalJ])) ;

            }

            printTryResult(correctNumberCorrectPlace, correctNumberWrongPlace);
            i++;


        }

        printTotalResult(findGeneratedCode);

    }

    /**
     * Method to see your try result
     *
     * @param correctNumberCorrectPlace is the number of correct number and correct place
     * @param correctNumberWrongPlace is the number of correct number and wrong place
     *
     * @author <a href="https://github.com/evrentan">Evren Tan</a>
     */
    private static void printTryResult(int correctNumberCorrectPlace, int correctNumberWrongPlace) {
        if (correctNumberCorrectPlace > 0)
            System.out.println(String.format("%d number(s) is/are correct and in the right place", correctNumberCorrectPlace));
        if (correctNumberWrongPlace > 0)
            System.out.println(String.format("%d number(s) is/are correct and in the wrong place", correctNumberWrongPlace));
        if (correctNumberCorrectPlace == 0 && correctNumberWrongPlace == 0)
            System.out.println("Nothing is correct");
    }

    /**
     * Method to see the final result
     *
     * @param findGeneratedCode boolean value whether you find the generatedCode or not
     *
     * @author <a href="https://github.com/evrentan">Evren Tan</a>
     */
    private static void printTotalResult(boolean findGeneratedCode) {
        if (findGeneratedCode)
            System.out.println("You did it, congrats !!!");
        else
            System.out.println("Not your best day, give another change !!!");
    }
}
