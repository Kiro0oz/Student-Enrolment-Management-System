package src;

import java.util.*;

public class Student {

    private String firstName;
    private String lastName;
    private int gradeYear;
    private static int id = 2025000;
    private int studentID;
    private List<String> courses = new ArrayList<>();
    private static final int COST_OF_COURSE = 600;
    private int tuitionBalance = 0;
    private int totalCreditHours = 0;
    private static final int MAX_CREDIT_HOURS = 18;
    private static final Scanner in = new Scanner(System.in);

    private static final Set<String> ELECTIVE_COURSES = Set.of(
    "Critical Thinking", "Humanities", "Management"
);

    // Predefined main courses per grade year
    private static final Map<Integer, List<String>> MAIN_COURSES = Map.of(
            1, List.of("Math 1", "Structure Programming", "Computer Science "),
            2, List.of("Math 3", "Software 1", "Data Structures"),
            3, List.of("Algorithms", "Operating Systems", "Database Systems"),
            4, List.of("AI & ML", "Cyber Security", "Advanced Software"));

    // Course credit hours
    private static final Map<String, Integer> COURSE_CREDITS = Map.ofEntries(
        entry("Math 1", 3), entry("Structure Programming", 3), entry("Computer Science ", 3),
        entry("Math 3", 3), entry("Software 1", 3), entry("Data Structures", 3),
        entry("Algorithms", 3), entry("Operating Systems", 3), entry("Database Systems", 3),
        entry("AI & ML", 3), entry("Cyber Security", 3), entry("Advanced Software", 3),
        entry("Critical Thinking", 0), entry("Humanities", 2), entry("Management", 2)
    );

    private static Map.Entry<String, Integer> entry(String key, Integer value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    // Constructor: Prompt user for student details
    public Student() {
        while (true) {
            System.out.print("\nEnter student first name: ");
            this.firstName = in.nextLine().trim();
            if (firstName.matches("[A-Za-z]+") && firstName.length() > 2) {
                break;
            } else {
                System.out.println("Invalid name!");
            }
        }

        while (true) {
            System.out.print("Enter student last name: ");
            this.lastName = in.nextLine().trim();
            if (lastName.matches("[A-Za-z]+") && lastName.length() > 2) {
                break;
            } else {
                System.out.println("Invalid name!");
            }
        }

        while (true) {
            System.out.print("\n1- Level 1\n2- Level 2\n3- Junior\n4- Senior\nEnter Student grade year: ");
            if (in.hasNextInt()) {
                this.gradeYear = in.nextInt();
                if (gradeYear >= 1 && gradeYear <= 4) {
                    in.nextLine();
                    break;
                } else {
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number (1-4).");
                in.nextLine();
            }
        }

        setId();
        enrollMainCourses();
        enrollAdditionalCourses();

        payTuition();
        return;
    }

    // Generate unique student ID
    private void setId() {
        id++;
        this.studentID = id;
    }

    // Automatically enrolls in main courses based on grade year
    private void enrollMainCourses() {
        List<String> mainCourses = MAIN_COURSES.get(gradeYear);
        if (mainCourses != null) {
            for (String course : mainCourses) {
                int creditHours = COURSE_CREDITS.get(course);
                courses.add(course);
                totalCreditHours += creditHours;
                tuitionBalance += creditHours * COST_OF_COURSE;
            }
            System.out.println("\nEnrolled in main courses: " + getFormattedCourses());
        }
    }

    // Allows students to add extra courses while checking credit limit
    public void enrollAdditionalCourses() {
        while (true) {
            System.out.println("\nAvailable Elective Courses: " + ELECTIVE_COURSES);
            System.out.print("\nEnter course to enroll (Q to quit): ");
            String course = in.nextLine().trim();
    
            if (course.equalsIgnoreCase("Q")) break;
    
            if (!ELECTIVE_COURSES.contains(course)) {
                System.out.println("Invalid course selection! Please choose from the available elective courses.");
                continue;
            }
    
            if (!COURSE_CREDITS.containsKey(course)) {
                System.out.println("Error: Course not found in the system. Please check with the administration.");
                continue;
            }
    
            int creditHours = COURSE_CREDITS.get(course); 
            if (totalCreditHours + creditHours > MAX_CREDIT_HOURS) {
                System.out.println("Cannot enroll! Exceeds 18 credit hour limit.");
            } else {
                courses.add(course);
                totalCreditHours += creditHours;
                tuitionBalance += creditHours * COST_OF_COURSE;
                System.out.println("Enrolled in: " + course);
            }
        }
    
        System.out.println("\nFinal Enrollment: " + getFormattedCourses());
        System.out.println("Total Credit Hours: " + totalCreditHours);
        System.out.println("Tuition Balance: $" + tuitionBalance);
    }
    
    // View balance
    public void viewBalance() {
        System.out.println("\n Your current balance: $" + tuitionBalance);
    }

    // Pay tuition
    public void payTuition() {
        int amount;

        while (true) {
            System.out.print("\nEnter amount to pay: $");

            if (in.hasNextInt()) {
                amount = in.nextInt();
                in.nextLine();

                if (amount < 0) {
                    System.out.println("Invalid amount! Payment cannot be negative.");
                } else if (amount > tuitionBalance) {
                    System.out.println("Invalid amount! You cannot pay more than your balance.");
                } else {
                    break;
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                in.nextLine();
            }
        }

        tuitionBalance -= amount;
        System.out.println("\nPayment of $" + amount + " received. Thank you!");
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
                tuitionBalance);
    }

    // Helper method to format courses
    private String getFormattedCourses() {
        return courses.isEmpty() ? "No courses enrolled" : String.join(", ", courses);
    }
}
