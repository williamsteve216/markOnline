package dtsw.school.markbackend.services;

import dtsw.school.markbackend.models.Teacher;
import dtsw.school.markbackend.payload.request.TeacherRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {
    Teacher createTeacher(TeacherRequest teacherRequest);
    Teacher updateTeacher(TeacherRequest teacherRequest, Long id);
    void deleteTeacher(Long id);
    Teacher getTeacherById(Long id);
    List<Teacher>getAllTeachers();
}
