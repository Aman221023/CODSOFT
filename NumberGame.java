                                       
                    //     TASK 1 :-       NUMBER GAME     


import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
            int attempts = 0;
            int maxAttempts = 9; // Limit the number of attempts
            boolean guessedCorrectly = false;

            System.out.println("\nI'm thinking of a number between 1 and 100. Can you guess it?");
            System.out.println("You have " + maxAttempts + " attempts. Good luck!");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                if (!scanner.hasNextInt()) { // Validate input
                    System.out.println("Please enter a valid number!");
                    scanner.next(); // Consume invalid input
                    continue;
                }
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    totalScore += (maxAttempts - attempts + 1); // Higher score for fewer attempts
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }

                System.out.println("Attempts remaining: " + (maxAttempts - attempts));
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The number was " + numberToGuess + ".");
            }

            System.out.print("Your total score is: " + totalScore + ". Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing! Your final score is: " + totalScore);
        scanner.close();
    }
}