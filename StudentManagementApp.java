import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("Roll Number: %d, Name: %s, Grade: %s", rollNumber, name, grade);
    }
}

class StudentManager {
    private List<Student> students = new ArrayList<>();
    private final String DATA_FILE = "student_data.txt";

    public StudentManager() {
        loadStudentData();
    }

    public void addStudent(String name, int rollNumber, String grade) {
        students.add(new Student(name, rollNumber, grade));
        saveStudentData();
    }

    public void removeStudent(int rollNumber) {
        boolean isRemoved = students.removeIf(student -> student.getRollNumber() == rollNumber);
        if (isRemoved) {
            saveStudentData();
        } else {
            System.out.println("No student found with the given roll number.");
        }
    }

    public Student findStudent(int rollNumber) {
        return students.stream()
                .filter(student -> student.getRollNumber() == rollNumber)
                .findFirst()
                .orElse(null);
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students are currently enrolled.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private void loadStudentData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                String name = details[0];
                int rollNumber = Integer.parseInt(details[1]);
                String grade = details[2];
                students.add(new Student(name, rollNumber, grade));
            }
        } catch (IOException e) {
            System.out.println("No previous student data found. Starting fresh.");
        }
    }

    private void saveStudentData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save student data.");
        }
    }
}

public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add a Student");
            System.out.println("2. Remove a Student");
            System.out.println("3. Search for a Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();

                    if (name.isEmpty() || grade.isEmpty()) {
                        System.out.println("Name and grade cannot be blank.");
                    } else {
                        manager.addStudent(name, rollNumber, grade);
                        System.out.println("Student added successfully!");
                    }
                    break;

                case 2:
                    System.out.print("Enter roll number to remove: ");
                    int rollToRemove = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    manager.removeStudent(rollToRemove);
                    break;

                case 3:
                    System.out.print("Enter roll number to search: ");
                    int rollToFind = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Student student = manager.findStudent(rollToFind);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("No student found with the given roll number.");
                    }
                    break;

                case 4:
                    manager.displayAllStudents();
                    break;

                case 5:
                    System.out.println("Exiting. Have a great day!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}