package dtsw.school.markbackend.payload.request;

import javax.validation.constraints.NotBlank;

public class ClasseStudentRequest {
    private long id;
    @NotBlank
    private long classeId;
    @NotBlank
    private long studentId;
    @NotBlank
    private String anneeScolaire;
    @NotBlank
    private String statut;

    public long getClasseId() {
        return classeId;
    }

    public void setClasseId(long classeId) {
        this.classeId = classeId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
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
