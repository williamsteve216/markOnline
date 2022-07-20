package dtsw.school.markbackend.services.impl;

import dtsw.school.markbackend.exceptions.ResourceNotFoundException;
import dtsw.school.markbackend.models.Teacher;
import dtsw.school.markbackend.payload.request.TeacherRequest;
import dtsw.school.markbackend.repository.TeacherDAO;
import dtsw.school.markbackend.services.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private TeacherDAO teacherDAO;

    public TeacherServiceImpl(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher createTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher(teacherRequest.getFirstName(),teacherRequest.getLastName(),teacherRequest.getGrade());
        teacher = teacherDAO.save(teacher);
        return teacher;
    }

    @Override
    public Teacher updateTeacher(TeacherRequest teacherRequest, Long id) {
        Teacher teacher = teacherDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Teacher","Id",id));
        teacher.setFirstName(teacherRequest.getFirstName());
        teacher.setLastName(teacherRequest.getLastName());
        teacher.setGrade(teacherRequest.getGrade());
        teacher = teacherDAO.save(teacher);
        return teacher;
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Teacher","Id",id));
        teacherDAO.deleteById(teacher.getId());
    }

    @Override
    public Teacher getTeacherById(Long id) {
        Teacher teacher = teacherDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Teacher","Id",id));
        return teacher;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teacherList = teacherDAO.findAll();
        return teacherList;
    }
}
