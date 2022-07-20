package dtsw.school.markbackend.services;

import dtsw.school.markbackend.models.Conseil;
import dtsw.school.markbackend.models.Course;
import dtsw.school.markbackend.payload.request.CourseRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    Course createCourse(CourseRequest courseRequest);
    Course updateCourse(CourseRequest courseRequest, Long id);
    void deleteCourse(Long id);
    Course getCourseById(Long id);
    List<Course> getAllCourses();

    Conseil createConseil(Conseil conseil);
    Conseil updateConseil(Conseil conseil, Long id);
    void deleteConseil(Long id);
    Conseil getConseilById(Long id);
    List<Conseil>getAllConseils();
}
