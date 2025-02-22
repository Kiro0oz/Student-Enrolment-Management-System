package src;
import java.util.Scanner;

public class StudentDatabaseApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Prompt user for the number of students
        System.out.print("\nHow many students do you want to add? ");
        int numOfStudents = input.nextInt();
        input.nextLine(); 
        Student[] students = new Student[numOfStudents];

        // Register students
        for (int i = 0; i < numOfStudents; i++) {
            System.out.println("\n Registering Student " + (i + 1) + "...");
            students[i] = new Student();  
            students[i].enrollCourses();  
            students[i].payTuition();     
            
            System.out.println(students[i].showInfo());  
            System.out.println(" Student added successfully!\n");
        }

        // Display all students summary
        System.out.println("\n All Registered Students:\n----------------------------------");
        for (Student student : students) {
            System.out.println(student.showInfo());
        }
        
        input.close(); 
    }
}
