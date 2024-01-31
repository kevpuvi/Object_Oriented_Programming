import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/voteDB";
    private static final String USER = "user367";
    private static final String PASSWORD = "Den030795!";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public boolean doesStudentExist(int studentId) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM studentDB.students WHERE studentID = ?")) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getStudentName(int studentId) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT name FROM studentDB.students WHERE studentID = ?")) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void displayNominees() {
        // Display all the nominees from the voteDB
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM nominees")) {

            System.out.println("The current nominees are:"); // <-- added this line

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("studentID") + ", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void voteForNominee(int studentId, int nomineeId, String comment) {
        // Check if the student is trying to vote for themselves
        if (studentId == nomineeId) {
            System.out.println("You can't vote for yourself!");
            return;
        }

        try (Connection conn = getConnection()) {
            // Check if the student already voted
            PreparedStatement checkVote = conn.prepareStatement("SELECT * FROM votes WHERE studentID = ?");
            checkVote.setInt(1, studentId);
            ResultSet rs = checkVote.executeQuery();
            if (rs.next()) {
                System.out.println("You've already voted!");
                return;
            }

            // Add vote and comment
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO votes(studentID, nomineeID, comment) VALUES (?, ?, ?)");
            stmt.setInt(1, studentId);
            stmt.setInt(2, nomineeId);
            stmt.setString(3, comment);
            stmt.executeUpdate();
            System.out.println("Your vote and comment have been registered!");

            // Mark the student as having voted
            setStudentHasVoted(studentId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setStudentHasVoted(int studentId) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE studentDB.students SET hasVoted = 1 WHERE studentID = ?")) {
            stmt.setInt(1, studentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Integer> getNomineeIds() {
        List<Integer> nomineeIds = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT studentID FROM nominees")) {
            while (rs.next()) {
                nomineeIds.add(rs.getInt("studentID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomineeIds;
    }

    public void proposeNominee(int nomineeId) {
        // Propose a new nominee (ensure they are not already a nominee)
        try (Connection conn = getConnection()) {
            // Check if the student is already a nominee
            PreparedStatement checkNominee = conn.prepareStatement("SELECT * FROM nominees WHERE studentID = ?");
            checkNominee.setInt(1, nomineeId);
            ResultSet rs = checkNominee.executeQuery();
            if (rs.next()) {
                System.out.println("This student has already been nominated!");
                return;
            }

            // Fetch student's name
            String studentName = getStudentName(nomineeId);
            if (studentName == null) {
                System.out.println("Invalid student ID!");
                return;
            }

            // Insert student as a nominee
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO nominees (studentID, name) VALUES (?, ?)");
            stmt.setInt(1, nomineeId);
            stmt.setString(2, studentName);
            stmt.executeUpdate();
            System.out.println("Nominee has been proposed and added to the database!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void displayLeadingNominee() {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT s.studentID, s.name, COUNT(v.nomineeID) as vote_count, GROUP_CONCAT(v.comment SEPARATOR ', ') as comments " +
                             "FROM studentDB.students s " +
                             "LEFT JOIN votes v ON s.studentID = v.nomineeID " +
                             "GROUP BY s.studentID, s.name " +
                             "ORDER BY vote_count DESC, s.studentID " +
                             "LIMIT 1")) {  // Only fetch the leading nominee
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Leading Nominee:");
                System.out.println("Student ID: " + rs.getInt("studentID") + ", Name: " + rs.getString("name"));
                System.out.println("Votes Received: " + rs.getInt("vote_count"));
                String comments = rs.getString("comments");
                if(comments != null && !comments.trim().isEmpty()) {
                    System.out.println("Comments: " + comments);
                }
                System.out.println("--------------------------------");
            } else {
                System.out.println("No votes have been cast yet.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
