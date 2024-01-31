import java.util.ArrayList;
import java.util.List;

// Nominee class that extends from Person and represents a nominated student for voting
public class Nominee extends Person {

    private Student student;           // The student who is nominated
    private int votesCount;            // Counter to track the number of votes for this nominee
    private List<String> comments;     // List to hold comments related to votes for the nominee

    // Constructor: Initialize the nominee with a Student instance
    public Nominee(Student student) {
        super(student.getId(), student.getName());  // Call the parent class constructor with student's ID and name
        this.student = student;                     // Assign student reference
        this.votesCount = 0;                        // Initialize votes count to zero
        this.comments = new ArrayList<>();          // Initialize empty comments list
    }

    // Get the total number of votes for the nominee
    public int getVotesCount() {
        return votesCount;
    }

    // Increment the votes count for the nominee
    public void addVote() {
        votesCount++;
    }

    // Add a comment related to the nominee's vote
    public void addComment(String comment) {
        comments.add(comment);
    }

    // Get a list of all comments related to the nominee's votes
    public List<String> getComments() {
        return new ArrayList<>(comments);
    }

    // Retrieve the name of the student who is the nominee
    public String getStudentName() {
        return student.getName();
    }
}
