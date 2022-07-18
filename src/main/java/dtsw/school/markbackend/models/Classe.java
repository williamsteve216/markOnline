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

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;


    public Classe(String name) {
        this.name = name;
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
}
