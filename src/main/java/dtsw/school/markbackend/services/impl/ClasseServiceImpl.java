package dtsw.school.markbackend.services.impl;

import dtsw.school.markbackend.exceptions.ClasseCourseException;
import dtsw.school.markbackend.exceptions.ResourceNotFoundException;
import dtsw.school.markbackend.models.*;
import dtsw.school.markbackend.payload.request.ClasseCourseTeacherRequest;
import dtsw.school.markbackend.payload.request.ClasseRequest;
import dtsw.school.markbackend.repository.ClasseDAO;
import dtsw.school.markbackend.repository.CourseDAO;
import dtsw.school.markbackend.repository.SchoolDAO;
import dtsw.school.markbackend.repository.TeacherDAO;
import dtsw.school.markbackend.services.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseServiceImpl implements ClasseService {
    private ClasseDAO classeDAO;

    @Autowired
    private SchoolDAO schoolDAO;
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private TeacherDAO teacherDAO;

    public ClasseServiceImpl(ClasseDAO classeDAO) {
        this.classeDAO = classeDAO;
    }

    @Override
    public Classe createClasse(ClasseRequest classeRequest) {
        School school = schoolDAO.findById(classeRequest.getSchoolId()).get();
        Classe classe = new Classe(
                classeRequest.getName(),
                classeRequest.getAnneeScolaire(),
                classeRequest.getNiveau(),
                school
        );
        classe = classeDAO.save(classe);
        return classe;
    }

    @Override
    public Classe updateClasse(ClasseRequest classeRequest, Long id) {
        Classe classe = classeDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Classe","Id",id));
        classe.setName(classeRequest.getName());
        classe = classeDAO.save(classe);
        return classe;
    }

    @Override
    public void deleteClasse(Long id) {
        Classe classe = classeDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Classe","Id",id));
        classeDAO.deleteById(id);
    }

    @Override
    public Classe getClasseById(Long id) {
        Classe classe = classeDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Classe","Id",id));
        return classe;
    }

    @Override
    public List<Classe> getAllClasses() {
        List<Classe> classeList = classeDAO.findAll();
        return classeList;
    }

    @Override
    public List<ClasseCourseTeacher> createClasseCourseTeacher(ClasseCourseTeacherRequest classeCourseTeacherRequest) {
        Classe classe = classeDAO.findById(classeCourseTeacherRequest.getClasseId()).orElseThrow(()->
                new ResourceNotFoundException("Classe","Id",classeCourseTeacherRequest.getClasseId()));
        Course course = courseDAO.findById(classeCourseTeacherRequest.getCourseId()).orElseThrow(()->
                new ResourceNotFoundException("Course","Id",classeCourseTeacherRequest.getCourseId()));
        Teacher teacher = null;
        if(classeCourseTeacherRequest.getTeacherId()!=0){
            teacher = teacherDAO.findById(classeCourseTeacherRequest.getTeacherId()).orElseThrow(()->
                    new ResourceNotFoundException("Teacher","Id",classeCourseTeacherRequest.getTeacherId()));
        }
        if(classe.existCourse(course)==false){
            if(teacher!=null){
                ClasseCourseTeacher classeCourseTeacher = new ClasseCourseTeacher(classe,course,teacher,course.getCoefficient());
                List<ClasseCourseTeacher>classeCourseTeachers = classe.addClasseCourseTeacher(classeCourseTeacher);
                classe.setClasseCourseTeachers(classeCourseTeachers);
                classe = classeDAO.save(classe);
                return classe.getClasseCourseTeachers();
            }
            else{
                ClasseCourseTeacher classeCourseTeacher = new ClasseCourseTeacher(classe,course,course.getCoefficient());
                List<ClasseCourseTeacher>classeCourseTeachers = classe.addClasseCourseTeacher(classeCourseTeacher);
                classe.setClasseCourseTeachers(classeCourseTeachers);
                classe = classeDAO.save(classe);
                return classe.getClasseCourseTeachers();
            }
        }
        else{
            throw  new ClasseCourseException();
        }
    }

    @Override
    public List<ClasseCourseTeacher> addTeacherToClasse(ClasseCourseTeacherRequest classeCourseTeacherRequest) {
        Classe classe = classeDAO.findById(classeCourseTeacherRequest.getClasseId()).orElseThrow(()->
                new ResourceNotFoundException("Classe","Id",classeCourseTeacherRequest.getClasseId()));
        Course course = courseDAO.findById(classeCourseTeacherRequest.getCourseId()).orElseThrow(()->
                new ResourceNotFoundException("Course","Id",classeCourseTeacherRequest.getCourseId()));
        Teacher teacher = teacherDAO.findById(classeCourseTeacherRequest.getTeacherId()).orElseThrow(()->
                new ResourceNotFoundException("Teacher","Id",classeCourseTeacherRequest.getTeacherId()));
        List<ClasseCourseTeacher> classeCourseTeachers = classe.addTeacher(teacher,course);
        classe.setClasseCourseTeachers(classeCourseTeachers);
        classe = classeDAO.save(classe);
        return classe.getClasseCourseTeachers();
    }

}
