package src;
import java.util.Scanner;

public class StudentDatabaseApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numOfStudents;

        System.out.print("\n=========================================== ");
        System.out.print("\nCairo University - Database administration ");
        System.out.print("\n===========================================\n ");

        // Prompt user for the number of students
        while (true) {
            System.out.print("\nHow many students do you want to add? ");
            if (input.hasNextInt()) { 
                numOfStudents = input.nextInt();
                if (numOfStudents > 0) { 
                    break;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
            input.nextLine(); 
        }

        System.out.println("You want to add " + numOfStudents + " students.");
        Student[] students = new Student[numOfStudents];

        // Register students
        for (int i = 0; i < numOfStudents; i++) {
            System.out.println("\n Registering Student " + (i + 1) + "...");
            students[i] = new Student();  
            System.out.println(students[i].showInfo());  
            System.out.println(" Student added successfully!\n");
        }

        input.close();

        // Display all students summary
        System.out.println("\n All Registered Students:\n----------------------------------");
        for (Student student : students) {
            System.out.println(student.showInfo());
        }
    }
}
