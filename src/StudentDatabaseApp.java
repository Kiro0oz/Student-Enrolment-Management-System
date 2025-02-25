package src;
import java.util.Scanner;

public class StudentDatabaseApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("\n=========================================== ");
        System.out.print("\nCairo University - Database Administration ");
        System.out.print("\n===========================================\n ");

        while (true) { 
            int numOfStudents;

            // Prompt user for the number of students
            while (true) {
                System.out.print("\nHow many students do you want to add? ");
                if (input.hasNextInt()) { 
                    numOfStudents = input.nextInt();
                    if (numOfStudents > 0) { 
                        input.nextLine(); 
                        break;
                    } else {
                        System.out.println("Please enter a positive number.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a valid integer.");
                    input.nextLine(); 
                }
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

            // Display all students summary
            System.out.println("\n All Registered Students:\n----------------------------------");
            for (Student student : students) {
                System.out.println(student.showInfo());
            }

            // Ask user if they want to add more students or exit
            while (true) {
                System.out.print("\nDo you want to add more students? (yes/no): ");
                String choice = input.nextLine().trim().toLowerCase();

                if (choice.equals("no")) {
                    System.out.println("\nExiting program. Goodbye!");
                    input.close();
                    return; 
                } else if (choice.equals("yes")) {
                    break; 
                } else {
                    System.out.println("Invalid choice! Please enter 'yes' or 'no'.");
                }
            }
        }
    }
}
