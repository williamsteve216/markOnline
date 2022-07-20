package dtsw.school.markbackend.models;


import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name = Conseil.CONSEIL_SEQUENCE_NAME, sequenceName = Conseil.CONSEIL_SEQUENCE_NAME)
public class Conseil extends CommonModel{
    public static final String CONSEIL_SEQUENCE_NAME="CONSEIL_SEQUENCE_NAME";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Conseil.CONSEIL_SEQUENCE_NAME)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "conseil")
    private List<Course> courses;

    public Conseil(String name) {
        this.name = name;
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
}
