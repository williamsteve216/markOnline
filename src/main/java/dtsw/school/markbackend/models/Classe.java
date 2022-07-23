package dtsw.school.markbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Entity
@SequenceGenerator(name = Classe.CLASSE_SEQUENCE_NAME, sequenceName = Classe.CLASSE_SEQUENCE_NAME)
public class Classe extends CommonModel {
    public static final String CLASSE_SEQUENCE_NAME ="CLASSE_SEQUENCE_NAME";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CLASSE_SEQUENCE_NAME)
    private long id;
    @Column(length = 50)
    private String name;
    @Column(length = 10)
    private String anneeScolaire;
    @Column(length = 15)
    private String niveau;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    private School school;

    @JsonIgnore
    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    private List<ClasseCourseTeacher> classeCourseTeachers;

    private List<ClasseStudent> classeStudents;

    public Classe() {
    }

    public Classe(String name) {
        this.name = name;
    }

    public Classe(String name, String anneeScolaire, String niveau, School school) {
        this.name = name;
        this.anneeScolaire = anneeScolaire;
        this.niveau = niveau;
        this.school = school;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public List<ClasseCourseTeacher> getClasseCourseTeachers() {
        return classeCourseTeachers;
    }

    public void setClasseCourseTeachers(List<ClasseCourseTeacher> classeCourseTeachers) {
        this.classeCourseTeachers = classeCourseTeachers;
    }

    public List<ClasseStudent> getClasseStudents() {
        return classeStudents;
    }

    public void setClasseStudents(List<ClasseStudent> classeStudents) {
        this.classeStudents = classeStudents;
    }

    public List<ClasseCourseTeacher> addClasseCourseTeacher(ClasseCourseTeacher classeCourseTeacher){
        this.classeCourseTeachers.add(classeCourseTeacher);
        return this.getClasseCourseTeachers();
    }
    public List<ClasseCourseTeacher> removeClasseCourseTeacher(ClasseCourseTeacher classeCourseTeacher){
        List<ClasseCourseTeacher> classeCourseTeachers = this.getClasseCourseTeachers();
        classeCourseTeachers.removeIf(element->classeCourseTeacher.getCourse().equals(element.getCourse()));
        return classeCourseTeachers;
    }
    public List<ClasseCourseTeacher> addTeacher(Teacher teacher, Course course){
        List<ClasseCourseTeacher> classeCourseTeachers = this.getClasseCourseTeachers();
        ListIterator<ClasseCourseTeacher> listIterator = classeCourseTeachers.listIterator();
        boolean found=false;
        while(listIterator.hasNext() && found==false){
            ClasseCourseTeacher classeCourseTeacher = listIterator.next();
            if(classeCourseTeacher.getCourse().equals(course)){
                found=true;
                classeCourseTeacher.setTeacher(teacher);
                listIterator.set(classeCourseTeacher);
            }
        }
        return classeCourseTeachers;
    }
    public List<ClasseCourseTeacher> removeTeacher(ClasseCourseTeacher classeCourseTeacher){
        List<ClasseCourseTeacher> classeCourseTeachers = this.getClasseCourseTeachers();
        ListIterator<ClasseCourseTeacher> listIterator = classeCourseTeachers.listIterator();
        boolean found=false;
        while(listIterator.hasNext() && found==false){
            ClasseCourseTeacher classeCourseTeacher1 = listIterator.next();
            if(classeCourseTeacher.getTeacher().equals(classeCourseTeacher1.getTeacher()) &&
                    classeCourseTeacher.getCourse().equals(classeCourseTeacher1.getCourse())){
                found=true;
                classeCourseTeacher.setTeacher(null);
                listIterator.set(classeCourseTeacher);
            }
        }
        return classeCourseTeachers;
    }
    public boolean existTeacherCourse(Teacher teacher, Course course){
        boolean found = false;
        Iterator<ClasseCourseTeacher> classeCourseTeacherIterator = this.getClasseCourseTeachers().iterator();
        while (classeCourseTeacherIterator.hasNext() && found==false){
            ClasseCourseTeacher classeCourseTeacher = classeCourseTeacherIterator.next();
            if(teacher.equals(classeCourseTeacher.getTeacher()) && course.equals(classeCourseTeacher.getCourse())){
                found=true;
            }
        }
        return found;
    }
    public boolean existCourse(Course course){
        boolean found = false;
        Iterator<ClasseCourseTeacher> classeCourseTeacherIterator = this.getClasseCourseTeachers().iterator();
        while (classeCourseTeacherIterator.hasNext() && found==false){
            ClasseCourseTeacher classeCourseTeacher = classeCourseTeacherIterator.next();
            if(course.equals(classeCourseTeacher.getCourse())){
                found=true;
            }
        }
        return found;
    }
    public List<ClasseStudent> enrollStudent(ClasseStudent classeStudent){
        List<ClasseStudent> classeStudents = this.getClasseStudents();
        classeStudents.add(classeStudent);
        return classeStudents;
    }
    public List<ClasseStudent> removeStudent(ClasseStudent classeStudent){
        List<ClasseStudent> classeStudents = this.getClasseStudents();
        classeStudents.removeIf(element->classeStudent.getStudent().equals(element.getStudent()));
        return classeStudents;
    }
    public boolean existStudent(Student student){
        boolean found=false;
        List<ClasseStudent> classeStudents = this.getClasseStudents();
        Iterator<ClasseStudent> classeStudentIterator = classeStudents.iterator();
        while (classeStudentIterator.hasNext() && found==false){
            ClasseStudent classeStudent = classeStudentIterator.next();
            if(classeStudent.getStudent().equals(student)){
                found=true;
            }
        }
        return found;
    }
    public String toString(){
        return name+" "+anneeScolaire+" "+niveau;
    }
}
