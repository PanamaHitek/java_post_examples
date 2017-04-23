package Java_PHP;

public class User {

    private int id;
    private String name;
    private String lastName;
    private String birthdate;
    private String gender;

    public User(int id, String name, String lastName, String birthdate, String gender) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
