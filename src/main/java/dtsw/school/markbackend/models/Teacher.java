package dtsw.school.markbackend.models;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = Teacher.TEACHER_SEQUENCE_NAME, sequenceName = Teacher.TEACHER_SEQUENCE_NAME)
public class Teacher extends CommonModel {
    public static final String TEACHER_SEQUENCE_NAME = "TEACHER_SEQUENCE_NAME";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Teacher.TEACHER_SEQUENCE_NAME)
    private long id;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    private String grade;
    private String matricule;

    public Teacher(String firstName, String lastName, String grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
