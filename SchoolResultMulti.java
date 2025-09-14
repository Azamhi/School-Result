import java.util.Scanner;

class Student {
    String name;
    int rollNo;
    int english, math, science, computer;
    int total;
    double percentage;
    String grade;
    String status; // Pass or Fail

    // Constructor
    Student(String name, int rollNo, int english, int math, int science, int computer) {
        this.name = name;
        this.rollNo = rollNo;
        this.english = english;
        this.math = math;
        this.science = science;
        this.computer = computer;
        calculateResult();
    }

    // Calculate total, percentage, grade, and pass/fail
    void calculateResult() {
        total = english + math + science + computer;
        percentage = total / 4.0;

        // Pass/Fail check
        if (english < 33 || math < 33 || science < 33 || computer < 33) {
            status = "Fail";
            grade = "F";
        } else {
            status = "Pass";
            if (percentage >= 90) grade = "A+";
            else if (percentage >= 80) grade = "A";
            else if (percentage >= 70) grade = "B";
            else if (percentage >= 60) grade = "C";
            else if (percentage >= 50) grade = "D";
            else grade = "F";
        }
    }

    void display() {
        System.out.printf("%-10s %-6d %-8d %-8d %-8d %-10d %-10d %-12.2f %-5s %-6s\n",
                name, rollNo, english, math, science, computer, total, percentage, grade, status);
    }
}

public class SchoolResultMulti {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        Student[] students = new Student[n];

        // Input details for each student
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Student " + (i + 1));
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Roll No: ");
            int rollNo = sc.nextInt();
            System.out.print("English Marks: ");
            int eng = sc.nextInt();
            System.out.print("Math Marks: ");
            int math = sc.nextInt();
            System.out.print("Science Marks: ");
            int sci = sc.nextInt();
            System.out.print("Computer Marks: ");
            int comp = sc.nextInt();
            sc.nextLine(); // consume newline

            students[i] = new Student(name, rollNo, eng, math, sci, comp);
        }

        // Print Result Sheet
        System.out.println("\n================= CLASS RESULT SHEET =================");
        System.out.printf("%-10s %-6s %-8s %-8s %-8s %-10s %-10s %-12s %-5s %-6s\n",
                "Name", "RollNo", "English", "Math", "Science", "Computer", "Total", "Percentage", "Grade", "Status");
        System.out.println("---------------------------------------------------------------------------------------");

        Student topper = null;
        for (Student s : students) {
            s.display();
            if (s.status.equals("Pass")) { // Only consider pass students for topper
                if (topper == null || s.percentage > topper.percentage) {
                    topper = s;
                }
            }
        }

        // Show Topper
        if (topper != null) {
            System.out.println("\n🏆 Class Topper: " + topper.name + " (Roll No: " + topper.rollNo +
                               ") with " + topper.percentage + "%");
        } else {
            System.out.println("\n⚠️ No topper as all students failed.");
        }

        sc.close();
    }
}

