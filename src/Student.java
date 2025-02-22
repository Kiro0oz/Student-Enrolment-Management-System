package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {

    private String firstName;
    private String lastName;
    private int gradeYear;
    private static int id = 2024000;
    private int studentID;
    private List<String> courses = new ArrayList<>();
    private static final int COST_OF_COURSE = 600;
    private int tuitionBalance = 0;
    
    private static final Scanner in = new Scanner(System.in);

    // Constructor: Prompt user for student details
    public Student() {
        System.out.print("\nEnter student first name: ");
        this.firstName = in.nextLine();

        System.out.print("Enter student last name: ");
        this.lastName = in.nextLine();

        System.out.print("\n1- Level 1\n2- Level 2\n3- Junior\n4- Senior\nEnter Student grade year: ");
        this.gradeYear = in.nextInt();
        in.nextLine(); // Consume newline

        setId();

        System.out.println("\n Student Registered Successfully!");
        System.out.println(showInfo());
    }

    // Generate unique student ID
    private void setId() {
        id++;
        this.studentID = id;
    }

    // Enroll in courses
    public void enrollCourses() {
        while (true) {
            System.out.print("\nEnter course to enroll (Q to quit): ");
            String course = in.nextLine();
            if (course.equalsIgnoreCase("Q")) break;
            
            courses.add(course);
            tuitionBalance += COST_OF_COURSE;

            System.out.println(" Enrolled in: " + course);
        }
        System.out.println("\n Current Enrollment: " + getFormattedCourses());
        System.out.println(" Tuition Balance: $" + tuitionBalance);
    }

    // View balance
    public void viewBalance() {
        System.out.println("\n Your current balance: $" + tuitionBalance);
    }

    // Pay tuition
    public void payTuition() {
        System.out.print("\n Enter amount to pay: $");
        int amount = in.nextInt();
        in.nextLine(); 

        tuitionBalance -= amount;
        System.out.println("\n Payment of $" + amount + " received. Thank you!");
        viewBalance();
    }

    // Display student information
    public String showInfo() {
        return String.format(
            "\n----------------------------------\n" +
            "           Student Info         \n" +
            "----------------------------------\n" +
            " Name      : %s %s\n" +
            " Level     : %d\n" +
            " ID        : %d\n" +
            " Courses   : %s\n" +
            " Balance   : $%d\n" +
            "----------------------------------\n",
            firstName, lastName, gradeYear, studentID, 
            getFormattedCourses(),
            tuitionBalance
        );
    }

    // Helper method to format courses
    private String getFormattedCourses() {
        return courses.isEmpty() ? "No courses enrolled" : String.join(", ", courses);
    }
}
