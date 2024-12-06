
                      //    TASK 2     Student Grade Calculator

import java.util.Scanner;

public class MarksCalculator {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        // Ask user for the number of subjects
        System.out.print("Please enter the total number of subjects: ");
        int subjectCount = inputScanner.nextInt();

        // Array to store marks for each subject
        int[] subjectMarks = new int[subjectCount];
        System.out.println("Enter the marks for each subject (maximum score 100):");
        for (int i = 0; i < subjectCount; i++) {
            System.out.print("Marks for Subject " + (i + 1) + ": ");
            subjectMarks[i] = inputScanner.nextInt();
        }

        // Compute total marks
        int sumOfMarks = 0;
        for (int mark : subjectMarks) {
            sumOfMarks += mark;
        }

        // Calculate average percentage
        double avgPercentage = (double) sumOfMarks / subjectCount;

        // Determine the grade based on the average percentage
        char studentGrade;
        if (avgPercentage >= 90) {
            studentGrade = 'A';
        } else if (avgPercentage >= 80) {
            studentGrade = 'B';
        } else if (avgPercentage >= 70) {
            studentGrade = 'C';
        } else if (avgPercentage >= 60) {
            studentGrade = 'D';
        } else {
            studentGrade = 'F';
        }

        // Output the results in a tabular format
        System.out.println("\n-----------------------------------------");
        System.out.printf("| %-18s | %-12s |\n", "Result Description", "Value");
        System.out.println("-----------------------------------------");
        System.out.printf("| %-18s | %-12d |\n", "Total Marks", sumOfMarks);
        System.out.printf("| %-18s | %-12.2f |\n", "Average Percentage", avgPercentage);
        System.out.printf("| %-18s | %-12c |\n", "Grade", studentGrade);
        System.out.println("-----------------------------------------");

        inputScanner.close();
    }
}