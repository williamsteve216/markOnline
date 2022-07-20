package dtsw.school.markbackend.services;

import dtsw.school.markbackend.models.Classe;
import dtsw.school.markbackend.models.ClasseCourseTeacher;
import dtsw.school.markbackend.payload.request.ClasseCourseTeacherRequest;
import dtsw.school.markbackend.payload.request.ClasseRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClasseService {
    Classe createClasse(ClasseRequest classeRequest);
    Classe updateClasse(ClasseRequest classeRequest, Long id);
    void deleteClasse(Long id);
    Classe getClasseById(Long id);
    List<Classe> getAllClasses();

    List<ClasseCourseTeacher>createClasseCourseTeacher(ClasseCourseTeacherRequest classeCourseTeacherRequest);
    List<ClasseCourseTeacher>addTeacherToClasse(ClasseCourseTeacherRequest classeCourseTeacherRequest);
}
