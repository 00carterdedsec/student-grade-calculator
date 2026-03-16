import java.util.Scanner;

/**
 * Student Grade Calculator
 * Accepts marks for multiple subjects, computes averages,
 * assigns letter grades, and prints a formatted report.
 *
 * Author: Moratehi Samuel Mahlangu
 */
public class GradeCalculator {

    // ── Constants ──────────────────────────────────────────────────
    static final String[] SUBJECTS = {
        "Mathematics",
        "English",
        "Computer Science",
        "Physics",
        "History"
    };

    // ── Grade logic ────────────────────────────────────────────────
    static String letterGrade(double mark) {
        if (mark >= 80) return "A";
        if (mark >= 70) return "B";
        if (mark >= 60) return "C";
        if (mark >= 50) return "D";
        return "F";
    }

    static String gradeRemark(String grade) {
        switch (grade) {
            case "A": return "Outstanding";
            case "B": return "Good";
            case "C": return "Average";
            case "D": return "Below Average";
            default:  return "Failing";
        }
    }

    // ── Input helper ───────────────────────────────────────────────
    static double getValidMark(Scanner sc, String subject) {
        double mark = -1;
        while (mark < 0 || mark > 100) {
            System.out.print("  Enter mark for " + subject + " (0 - 100): ");
            if (sc.hasNextDouble()) {
                mark = sc.nextDouble();
                if (mark < 0 || mark > 100) {
                    System.out.println("  [!] Mark must be between 0 and 100. Try again.");
                }
            } else {
                System.out.println("  [!] Invalid input. Please enter a number.");
                sc.next(); // discard bad token
            }
        }
        return mark;
    }

    // ── Report printer ─────────────────────────────────────────────
    static void printReport(String name, double[] marks) {
        double total = 0;
        for (double m : marks) total += m;
        double average = total / marks.length;
        String finalGrade = letterGrade(average);

        String border = "=".repeat(52);
        String thin   = "-".repeat(52);

        System.out.println("\n" + border);
        System.out.printf("  STUDENT REPORT CARD%n");
        System.out.println(thin);
        System.out.printf("  Student : %s%n", name);
        System.out.printf("  Subjects: %d%n", marks.length);
        System.out.println(thin);
        System.out.printf("  %-20s  %6s  %5s  %s%n", "Subject", "Mark", "Grade", "Remark");
        System.out.println(thin);

        for (int i = 0; i < SUBJECTS.length; i++) {
            String grade = letterGrade(marks[i]);
            System.out.printf("  %-20s  %5.1f%%  %5s  %s%n",
                SUBJECTS[i], marks[i], grade, gradeRemark(grade));
        }

        System.out.println(thin);
        System.out.printf("  %-20s  %5.1f%%  %5s  %s%n",
            "AVERAGE", average, finalGrade, gradeRemark(finalGrade));
        System.out.println(border);

        // Pass / Fail banner
        if (average >= 50) {
            System.out.println("  ✔  RESULT: PASS — Well done, " + name.split(" ")[0] + "!");
        } else {
            System.out.println("  ✘  RESULT: FAIL — Keep pushing, " + name.split(" ")[0] + ".");
        }
        System.out.println(border + "\n");
    }

    // ── Main ───────────────────────────────────────────────────────
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     STUDENT GRADE CALCULATOR v1.0   ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();

        boolean running = true;

        while (running) {
            // Get student name
            System.out.print("Enter student name: ");
            sc.nextLine(); // flush
            String name = sc.nextLine().trim();
            if (name.isEmpty()) name = "Unknown Student";

            // Collect marks
            System.out.println("\nEnter marks for each subject:\n");
            double[] marks = new double[SUBJECTS.length];
            for (int i = 0; i < SUBJECTS.length; i++) {
                marks[i] = getValidMark(sc, SUBJECTS[i]);
            }

            // Print report
            printReport(name, marks);

            // Ask to continue
            System.out.print("Calculate for another student? (y/n): ");
            String again = sc.next().trim().toLowerCase();
            running = again.equals("y") || again.equals("yes");
            System.out.println();
        }

        System.out.println("Thank you for using the Grade Calculator. Goodbye!");
        sc.close();
    }
}
