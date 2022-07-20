package dtsw.school.markbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = ClasseCourseTeacher.CLASSE_COURSE_TEACHER_SEQUENCE_NAME, sequenceName = ClasseCourseTeacher.CLASSE_COURSE_TEACHER_SEQUENCE_NAME)
@Table(name = "teaching")
public class ClasseCourseTeacher extends CommonModel {
    public static final String CLASSE_COURSE_TEACHER_SEQUENCE_NAME = "CLASSE_COURSE_TEACHER_SEQUENCE_NAME";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ClasseCourseTeacher.CLASSE_COURSE_TEACHER_SEQUENCE_NAME)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private int coefficient;

    public ClasseCourseTeacher() {
    }

    public ClasseCourseTeacher(Classe classe, Course course, int coefficient) {
        this.classe = classe;
        this.course = course;
        this.coefficient = coefficient;
    }

    public ClasseCourseTeacher(Classe classe, Course course, Teacher teacher, int coefficient) {
        this.classe = classe;
        this.course = course;
        this.teacher = teacher;
        this.coefficient = coefficient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
}
