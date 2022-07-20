package dtsw.school.markbackend.services.impl;

import dtsw.school.markbackend.exceptions.ResourceNotFoundException;
import dtsw.school.markbackend.models.Conseil;
import dtsw.school.markbackend.models.Course;
import dtsw.school.markbackend.payload.request.CourseRequest;
import dtsw.school.markbackend.repository.ConseilDAO;
import dtsw.school.markbackend.repository.CourseDAO;
import dtsw.school.markbackend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    private CourseDAO courseDAO;
    @Autowired
    private ConseilDAO conseilDAO;

    public CourseServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public Course createCourse(CourseRequest courseRequest) {
        Course course = new Course(courseRequest.getName(), courseRequest.getCoefficient());
        course = courseDAO.save(course);
        return course;
    }

    @Override
    public Course updateCourse(CourseRequest courseRequest, Long id) {
        Course course = courseDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Course","Id",id));
        course.setName(courseRequest.getName());
        course.setCoefficient(courseRequest.getCoefficient());
        course = courseDAO.save(course);
        return course;
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Course","Id",id));
        courseDAO.deleteById(id);
    }

    @Override
    public Course getCourseById(Long id) {
        Course course = courseDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Course","Id",id));

        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courseList = courseDAO.findAll();
        return courseList;
    }

    /*=========================================================================*/
    /*                      CONSEIL IMPLEMENTATION                             */
    /*=========================================================================*/
    @Override
    public Conseil createConseil(Conseil conseilRequest) {
        Conseil conseil = conseilDAO.save(conseilRequest);
        return conseil;
    }

    @Override
    public Conseil updateConseil(Conseil conseilRequest, Long id) {
        Conseil conseil = conseilDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Conseil","Id",id));
        conseil.setName(conseilRequest.getName());
        conseil = conseilDAO.save(conseil);
        return conseil;
    }

    @Override
    public void deleteConseil(Long id) {
        Conseil conseil = conseilDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Conseil","id",id));
        conseilDAO.deleteById(conseil.getId());
    }

    @Override
    public Conseil getConseilById(Long id) {
        Conseil conseil = conseilDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Conseil","Id",id));
        return conseil;
    }

    @Override
    public List<Conseil> getAllConseils() {
        List<Conseil> conseilList = conseilDAO.findAll();
        return conseilList;
    }
}
