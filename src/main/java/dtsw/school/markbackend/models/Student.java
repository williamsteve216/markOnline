package dtsw.school.markbackend.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@SequenceGenerator(name = Student.STUDENT_SEQUENCE_NAME, sequenceName = Student.STUDENT_SEQUENCE_NAME)
public class Student extends CommonModel{
    public static final String STUDENT_SEQUENCE_NAME="STUDENT_SEQUENCE_NAME";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Student.STUDENT_SEQUENCE_NAME)
    private Long id;

    private String identityCard;
    private String matricule;
    @Column(length = 100, nullable = false)
    private String firstName;
    private String lastName;
    private char Sexe;

    private String placeOfBirth;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String profile;
    private String phone;
    @Column(unique = true)
    private String email;

    private String ville;
    private String quartier;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, columnDefinition = "varchar(30) default 'ROLE_STUDENT'")
    private ERole role;

    @OneToMany(mappedBy = "student")
    private List<ClasseStudent> classeStudents;

    public Student(String firstName, String lastName, char sexe, String placeOfBirth, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        Sexe = sexe;
        this.placeOfBirth = placeOfBirth;
        this.dateOfBirth = dateOfBirth;
    }

    public Student(String identityCard, String firstName, String lastName, char sexe, String placeOfBirth, Date dateOfBirth, String profile, String phone, String email, String ville, String quartier) {
        this.identityCard = identityCard;
        this.firstName = firstName;
        this.lastName = lastName;
        Sexe = sexe;
        this.placeOfBirth = placeOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.profile = profile;
        this.phone = phone;
        this.email = email;
        this.ville = ville;
        this.quartier = quartier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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

    public char getSexe() {
        return Sexe;
    }

    public void setSexe(char sexe) {
        Sexe = sexe;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ClasseStudent> getClasseStudents() {
        return classeStudents;
    }

    public void setClasseStudents(List<ClasseStudent> classeStudents) {
        this.classeStudents = classeStudents;
    }
}
