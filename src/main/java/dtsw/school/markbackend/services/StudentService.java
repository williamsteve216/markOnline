package dtsw.school.markbackend.services;

import dtsw.school.markbackend.models.Student;
import dtsw.school.markbackend.payload.request.StudentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    Student createStudent(StudentRequest studentRequest);
    Student updateStudent(StudentRequest studentRequest, Long id);
    void deleteStudent(Long id);
    Student getStudentById(Long id);
    List<Student>getAllStudents();
}
