// Student class extending from the Person class
public class Student extends Person {

    private int id;  // Unique identifier for the student
    private String name;  // Name of the student
    private String studyProgram;  // The program of study the student is enrolled in
    private boolean hasVoted;  // Boolean to check if student has voted or not

    // Constructor to initialize the student object
    public Student (int id, String name, String studyProgram) {
        super(id, name);  // Calls the constructor of the parent class (Person)
        this.studyProgram = studyProgram;  // Set the study program
    }

    // Get the student's ID
    public int getId() {
        return id;
    }

    // Set the student's ID
    public void setId(int id) {
        this.id = id;
    }

    // Get the student's name
    public String getName() {
        return name;
    }

    // Set the student's name
    public void setName(String name) {
        this.name = name;
    }

    // Get the student's study program
    public String getStudyProgram() {
        return studyProgram;
    }

    // Set the student's study program
    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    // Check if the student has voted
    public boolean hasVoted() {
        return hasVoted;
    }

    // Set the student's voting status
    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
}
