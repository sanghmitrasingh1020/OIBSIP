import java.util.*;

public class GuessTheNumber {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("...Let's Play a Fun Game...");
        System.out.println("________");

        guessNum();
    }

    private static void guessNum() {
        int number = 1 + (int) (100 * Math.random());

        int Number_of_attempt = 10;
        System.out.println("Before the start of Game Keep these points on your mind ");
        System.out.println("-- You have to Guess a Number from 1 to 100 only");
        System.out.println("-- You will be allowed for only " + Number_of_attempt
                + " Attempts, after that no attempt will be considered");
        System.out.println();
        System.out.println("You can start the Game now.....");

        int i;
        for (i = 1; i <= Number_of_attempt; i++) {
            System.out.println("Guess a Number");
            int guess_No = scn.nextInt();
            if (number > guess_No && i != Number_of_attempt) {
                System.out.println("The number is greater than " + guess_No);
            } else if (number < guess_No && i != Number_of_attempt) {
                System.out.println("The number is less than " + guess_No);
            } else if (number == guess_No) {
                break;
            }
        }
        float score = (i * 100) / Number_of_attempt;
        System.out.println("________");
        if (i - 1 == Number_of_attempt) {
            System.out.println("Sorry ,No any more attempts are left And your Accuracy is" + 0 + "%");
        } else {
            System.out.println("Congratulations! You have guessed the Number Successfully in " + i
                    + " attempts And your Accuracy is " + score + " %");
        }
        System.out.println("The number was " + number);

    }
}