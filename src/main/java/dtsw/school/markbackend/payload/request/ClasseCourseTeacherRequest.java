package dtsw.school.markbackend.payload.request;

import javax.validation.constraints.NotBlank;

public class ClasseCourseTeacherRequest {
    private long id;
    @NotBlank
    private long classeId;
    @NotBlank
    private long courseId;
    private long teacherId=0;


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getClasseId() {
        return classeId;
    }

    public void setClasseId(Long classeId) {
        this.classeId = classeId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
