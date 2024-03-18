import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;
    private Scanner scanner;

    public StudentManagementSystem() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addStudent() {
        System.out.println("Adding a new student:");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        int rollNumber = getValidRollNumber();
        System.out.print("Enter student grade: ");
        String grade = scanner.nextLine();

        students.add(new Student(name, rollNumber, grade));
        System.out.println("Student added successfully.");
    }

    private int getValidRollNumber() {
        int rollNumber = 0;
        while (true) {
            try {
                System.out.print("Enter student roll number: ");
                rollNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Roll number must be an integer.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        return rollNumber;
    }

    public void removeStudent() {
        System.out.println("Removing a student:");
        int rollNumber = getValidRollNumber();

        boolean removed = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNumber() == rollNumber) {
                students.remove(i);
                System.out.println("Student removed successfully.");
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    public void searchStudent() {
        System.out.println("Searching for a student:");
        int rollNumber = getValidRollNumber();

        boolean found = false;
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Student found:");
                System.out.println(student);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("List of all students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public void exit() {
        System.out.println("Exiting the Student Management System. Goodbye!");
        scanner.close();
        System.exit(0);
    }
}

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add a new student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    sms.addStudent();
                    break;
                case 2:
                    sms.removeStudent();
                    break;
                case 3:
                    sms.searchStudent();
                    break;
                case 4:
                    sms.displayAllStudents();
                    break;
                case 5:
                    sms.exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}
