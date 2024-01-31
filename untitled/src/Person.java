// Base class representing a person
public class Person {
    private int id;       // Unique identifier for a person
    private String name;  // Name of the person

    // Constructor to initialize the person object with an ID and name
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Retrieve the person's ID
    public int getId() {
        return id;
    }

    // Retrieve the person's name
    public String getName() {
        return name;
    }
}
