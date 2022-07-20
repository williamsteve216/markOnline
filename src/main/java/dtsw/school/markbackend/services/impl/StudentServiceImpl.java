package dtsw.school.markbackend.services.impl;

import dtsw.school.markbackend.exceptions.ResourceNotFoundException;
import dtsw.school.markbackend.models.Student;
import dtsw.school.markbackend.payload.request.StudentRequest;
import dtsw.school.markbackend.repository.StudentDAO;
import dtsw.school.markbackend.services.StudentService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO;

    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student createStudent(StudentRequest studentRequest) {
        try{
            Date dateOfBirth=new SimpleDateFormat("dd/MM/yyyy").parse(studentRequest.getDateOfBirth());
            Student student = new Student(
                    studentRequest.getFirstName(),
                    studentRequest.getLastName(),
                    studentRequest.getSexe(),
                    studentRequest.getPlaceOfBirth(),
                    dateOfBirth
            );
            student = studentDAO.save(student);
            return student;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Student updateStudent(StudentRequest studentRequest, Long id){
        try {
            Date dateOfBirth=new SimpleDateFormat("dd/MM/yyyy").parse(studentRequest.getDateOfBirth());
            Student student = studentDAO.findById(id).orElseThrow(()->
                    new ResourceNotFoundException("Student","Id",id));
            student.setFirstName(studentRequest.getFirstName());
            student.setLastName(studentRequest.getLastName());
            student.setSexe(studentRequest.getSexe());
            student.setDateOfBirth(dateOfBirth);
            student.setPlaceOfBirth(studentRequest.getPlaceOfBirth());
            student = studentDAO.save(student);
            return student;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Student","Id",id));
        studentDAO.deleteById(student.getId());
    }

    @Override
    public Student getStudentById(Long id) {
        Student student = studentDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Student","Id",id));
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = studentDAO.findAll();
        return studentList;
    }
}
