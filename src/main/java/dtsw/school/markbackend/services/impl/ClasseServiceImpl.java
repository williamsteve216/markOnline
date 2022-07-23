package dtsw.school.markbackend.services.impl;

import dtsw.school.markbackend.exceptions.ClasseCourseException;
import dtsw.school.markbackend.exceptions.ExistException;
import dtsw.school.markbackend.exceptions.ResourceNotFoundException;
import dtsw.school.markbackend.models.*;
import dtsw.school.markbackend.payload.request.ClasseCourseTeacherRequest;
import dtsw.school.markbackend.payload.request.ClasseRequest;
import dtsw.school.markbackend.payload.request.ClasseStudentRequest;
import dtsw.school.markbackend.repository.*;
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
    @Autowired
    private StudentDAO studentDAO;

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

    @Override
    public List<ClasseCourseTeacher> removeTeacherToClasse(ClasseCourseTeacherRequest classeCourseTeacherRequest) {
        Classe classe = classeDAO.findById(classeCourseTeacherRequest.getClasseId()).orElseThrow(()->
                new ResourceNotFoundException("Classe","Id",classeCourseTeacherRequest.getClasseId()));
        Course course = courseDAO.findById(classeCourseTeacherRequest.getCourseId()).orElseThrow(()->
                new ResourceNotFoundException("Course","Id",classeCourseTeacherRequest.getCourseId()));
        Teacher teacher = teacherDAO.findById(classeCourseTeacherRequest.getTeacherId()).orElseThrow(()->
                new ResourceNotFoundException("Teacher","Id",classeCourseTeacherRequest.getTeacherId()));
        ClasseCourseTeacher classeCourseTeacher = new ClasseCourseTeacher(classe,course,teacher, course.getCoefficient());
        if(classe.existTeacherCourse(teacher,course)){
            List<ClasseCourseTeacher> classeCourseTeachers = classe.removeTeacher(classeCourseTeacher);
            classe.setClasseCourseTeachers(classeCourseTeachers);
            classe = classeDAO.save(classe);
            return classe.getClasseCourseTeachers();
        }
        else{
            throw new ResourceNotFoundException("Teacher course","Classe");
        }
    }

    @Override
    public List<ClasseStudent> enrollStudentToClasse(ClasseStudentRequest classeStudentRequest) {
        Classe classe = classeDAO.findById(classeStudentRequest.getClasseId()).orElseThrow(()->
                new ResourceNotFoundException("Classe","Id",classeStudentRequest.getClasseId()));
        Student student = studentDAO.findById(classeStudentRequest.getStudentId()).orElseThrow(()->
                new ResourceNotFoundException("Student","Id",classeStudentRequest.getStudentId()));
        ClasseStudent classeStudent = new ClasseStudent(student,classe,classeStudentRequest.getAnneeScolaire(),classeStudentRequest.getStatut());
        if(!classe.existStudent(student)){
            List<ClasseStudent> classeStudents = classe.enrollStudent(classeStudent);
            classe.setClasseStudents(classeStudents);
            classe = classeDAO.save(classe);
            return classe.getClasseStudents();
        }else{
            throw new ExistException("Student","Classe");
        }
    }

    @Override
    public List<ClasseStudent> removeStudentToClasse(ClasseStudentRequest classeStudentRequest) {
        Classe classe = classeDAO.findById(classeStudentRequest.getClasseId()).orElseThrow(()->
                new ResourceNotFoundException("Classe","Id",classeStudentRequest.getClasseId()));
        Student student = studentDAO.findById(classeStudentRequest.getStudentId()).orElseThrow(()->
                new ResourceNotFoundException("Student","Id",classeStudentRequest.getStudentId()));
        if(classe.existStudent(student)){
            ClasseStudent classeStudent = new ClasseStudent(student,classe);
            List<ClasseStudent> classeStudents = classe.removeStudent(classeStudent);
            classe.setClasseStudents(classeStudents);
            classe = classeDAO.save(classe);
            return classe.getClasseStudents();
        }
        else{
            throw new ResourceNotFoundException("Student","Classe");
        }
    }

}
