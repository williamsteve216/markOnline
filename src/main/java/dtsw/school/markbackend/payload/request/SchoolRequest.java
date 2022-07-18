package dtsw.school.markbackend.payload.request;

public class SchoolRequest {
    private long id;
    private String name;
    private String email;
    private String division;
    private String subdivision;

    public SchoolRequest(String name, String email, String division, String subdivision) {
        this.name = name;
        this.email = email;
        this.division = division;
        this.subdivision = subdivision;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDivision() {
        return division;
    }

    public String getSubdivision() {
        return subdivision;
    }
}
