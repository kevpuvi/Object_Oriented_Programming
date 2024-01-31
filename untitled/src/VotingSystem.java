import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VotingSystem {

    private List<Student> students;  // List of all registered students
    private List<Nominee> nominees;  // List of all nominated students

    // Default constructor
    public VotingSystem() {
        this.students = new ArrayList<>();
        this.nominees = new ArrayList<>();
    }

    // Add a student to the list
    public void addStudent(Student student) {
        this.students.add(student);
    }

    // Print all nominees and their respective vote counts
    public void listNominees() {
        for (Nominee nominee : nominees) {
            System.out.println(nominee.getStudentName() + " - Votes: " + nominee.getVotesCount());
        }
    }

    // Propose a new nominee; return false if nominee already exists
    public boolean proposeNewNominee(Student student) {
        for (Nominee nominee : nominees) {
            if (nominee.getStudentName().equals(student.getName())) {
                return false;  // Nominee already exists
            }
        }
        this.nominees.add(new Nominee(student));
        return true;
    }

    // Cast a vote for a nominee, if voter hasn't voted yet
    public boolean voteForNominee(Student voter, Nominee nominee, String comment) {
        if (!voter.hasVoted()) {
            nominee.addVote();
            nominee.addComment(comment);
            voter.setHasVoted(true);
            return true;
        }
        return false;  // Voter has already voted
    }

    // Return the list of all students
    public List<Student> getAllStudents() {
        return students;
    }

    // Retrieve a student by their ID
    public Optional<Student> getStudentById(int id) {
        return students.stream().filter(student -> student.getId() == id).findFirst();
    }

    // Retrieve a nominee by their name
    public Optional<Nominee> getNomineeByName(String name) {
        return nominees.stream().filter(nominee -> nominee.getStudentName().equals(name)).findFirst();
    }
}
