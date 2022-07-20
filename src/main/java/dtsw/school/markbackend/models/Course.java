package dtsw.school.markbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name = Course.COURSE_SEQUENCE_NAME, sequenceName =Course.COURSE_SEQUENCE_NAME )
public class Course extends CommonModel{
    public static final String COURSE_SEQUENCE_NAME="COURSE_SEQUENCE_NAME";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Course.COURSE_SEQUENCE_NAME)
    private Long id;
    @Column(length = 255)
    private String name;
    private int coefficient;

    @ManyToOne
    @JoinColumn(name = "conseil_id")
    private Conseil conseil;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<ClasseCourseTeacher> classeCourseTeachers;

    public Course() {
    }

    public Course(String name, int coefficient) {
        this.name = name;
        this.coefficient = coefficient;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
}
