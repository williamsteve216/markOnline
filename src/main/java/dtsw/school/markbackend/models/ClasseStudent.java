package dtsw.school.markbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = ClasseStudent.CLASSE_STUDENT_SEQUENCE_NAME, sequenceName = ClasseStudent.CLASSE_STUDENT_SEQUENCE_NAME)
public class ClasseStudent extends CommonModel{
    public static final String CLASSE_STUDENT_SEQUENCE_NAME="CLASSE_STUDENT_SEQUENCE_NAME";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ClasseStudent.CLASSE_STUDENT_SEQUENCE_NAME)
    private long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classe_id")
    private Classe classe;
    @Column(length = 10)
    private String anneeScolaire;

    @Column(length = 16)
    private String statut;

    public ClasseStudent() {
    }

    public ClasseStudent(Student student, Classe classe) {
        this.student = student;
        this.classe = classe;
    }

    public ClasseStudent(Student student, Classe classe, String anneeScolaire) {
        this.student = student;
        this.classe = classe;
        this.anneeScolaire = anneeScolaire;
    }

    public ClasseStudent(Student student, Classe classe, String anneeScolaire, String statut) {
        this.student = student;
        this.classe = classe;
        this.anneeScolaire = anneeScolaire;
        this.statut = statut;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
