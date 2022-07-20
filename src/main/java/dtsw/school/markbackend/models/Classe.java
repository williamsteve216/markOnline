package dtsw.school.markbackend.models;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;


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
}
