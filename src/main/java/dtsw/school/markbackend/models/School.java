package dtsw.school.markbackend.models;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name = School.SCHOOL_SEQUENCE_NAME, sequenceName = School.SCHOOL_SEQUENCE_NAME)
public class School extends CommonModel{
    public static final String SCHOOL_SEQUENCE_NAME="SCHOOL_SEQUENCE_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SCHOOL_SEQUENCE_NAME)
    private long id;
    @Column(length = 100, unique = true)
    private String name;
    @Column(length = 100, unique = true)
    private String email;
    private String division;
    private String subdivision;

    @OneToMany(mappedBy = "school")
    private List<Classe> classeList;

    public School() {
    }

    public School(String name, String email, String division, String subdivision) {
        this.name = name;
        this.email = email;
        this.division = division;
        this.subdivision = subdivision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public List<Classe> getClasseList() {
        return classeList;
    }

    public void setClasseList(List<Classe> classeList) {
        this.classeList = classeList;
    }
}
