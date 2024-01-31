import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseConnector db = new DatabaseConnector();

        System.out.println("Welcome to the Student of the Year voting system!");

        // Sign-in process
        System.out.println("Enter your Student ID to sign in: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (!db.doesStudentExist(studentId)) {
            System.out.println("You're not a registered student. Exiting...");
            return;
        }
        else {
            String studentName = db.getStudentName(studentId);
            if (studentName != null) {
                System.out.println("Welcome, " + studentName + "!");
            } else {
                System.out.println("Welcome, Student " + studentId + "!");
            }
        }
        // Menu for voting and other options
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View current Nominees");
            System.out.println("2. Vote for a Nominee");
            System.out.println("3. Propose a new Nominee");
            System.out.println("4. See current number of votes and comments for the leading nominee");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    // Display nominees (You'd need a method in DatabaseConnector for this)
                    db.displayNominees();
                    break;
                case 2:
                    // Display nominees and allow voting (using studentId for verification if they've voted)
                    List<Integer> availableNomineeIds = db.getNomineeIds();
                    if (availableNomineeIds.isEmpty()) {
                        System.out.println("No nominees available yet!");
                        break;
                    }
                    System.out.println("Available Nominees to vote for:");
                    db.displayNominees();
                    System.out.print("Enter the ID of the Nominee you want to vote for: ");
                    int nomineeId = scanner.nextInt();
                    scanner.nextLine();  // consume newline

                    // Check if the entered ID is valid
                    if (!availableNomineeIds.contains(nomineeId)) {
                        System.out.println("Invalid Nominee ID. Please select a valid Nominee.");
                        break;
                    }

                    System.out.print("Any comment about the nominee? ");
                    String comment = scanner.nextLine();
                    db.voteForNominee(studentId, nomineeId, comment);
                    break;

                case 3:
                    // Propose new nominee (using studentId to prevent self-nomination)
                    System.out.print("Enter the ID of the student you want to nominate: ");
                    int proposedNomineeId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    if (proposedNomineeId != studentId) {
                        db.proposeNominee(proposedNomineeId);
                    } else {
                        System.out.println("You cannot nominate yourself!");
                    }
                    break;
                case 4:
                    // Display nominees and their votes and comments
                    db.displayLeadingNominee();
                    break;

                case 6:
                    System.out.println("Thank you for participating! Goodbye.");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }


        }
    }
}
