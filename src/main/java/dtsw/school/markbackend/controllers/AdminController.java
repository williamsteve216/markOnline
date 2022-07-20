package dtsw.school.markbackend.controllers;

import dtsw.school.markbackend.exceptions.ResourceNotFoundException;
import dtsw.school.markbackend.models.Conseil;
import dtsw.school.markbackend.models.Course;
import dtsw.school.markbackend.models.School;
import dtsw.school.markbackend.payload.request.CourseRequest;
import dtsw.school.markbackend.payload.request.SchoolRequest;
import dtsw.school.markbackend.payload.response.ResponseStatus;
import dtsw.school.markbackend.payload.response.RestResponse;
import dtsw.school.markbackend.services.CourseService;
import dtsw.school.markbackend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin")
@RestController
public class AdminController {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private CourseService courseService;

    /*=======================================================================*/
    /*                          SCHOOL API                                   */
    /*=======================================================================*/
    //build create school REST API
    @PostMapping("/school")
    public RestResponse saveSchool(@RequestBody SchoolRequest schoolRequest){
        School schoolResult = schoolService.saveSchool(schoolRequest);
        return new RestResponse(schoolResult,"School created successfully", ResponseStatus.SUCCESS,201);
    }
    //build update school REST API
    @PutMapping("/school/{id}")
    public RestResponse updateSchool(@RequestBody SchoolRequest schoolRequest, @PathVariable("id") Long id) throws ResourceNotFoundException{
        try{
            School school = schoolService.updateSchool(schoolRequest,id);
            return new RestResponse(school,"School updated successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,400);
        }
    }
    //build delete school REST API
    @DeleteMapping("/school/{id}")
    public RestResponse deleteSchool(@PathVariable("id") Long id) throws ResourceNotFoundException{
        try{
            schoolService.deleteSchool(id);
            return new RestResponse("School deleted successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,400);
        }
    }
    //build get all school REST API
    @GetMapping("/schools")
    public RestResponse getAllSchool(){
        List<School> schoolList = schoolService.getAllSchool();
        return new RestResponse(schoolList,"Displayed all school successfully",ResponseStatus.SUCCESS,200);
    }
    //build get one school REST API
    @GetMapping("/school/{id}")
    public RestResponse getSchoolById(@PathVariable("id") Long id) throws ResourceNotFoundException {
       try {
           School school = schoolService.getSchoolById(id);
           return new RestResponse(school,"School found successfuly",ResponseStatus.SUCCESS,200);
       }catch (ResourceNotFoundException e){
           return new RestResponse(e.getMessage(),ResponseStatus.ABORTED,404);
       }
    }

    /*=======================================================================*/
    /*                          COURSE API                                   */
    /*=======================================================================*/
    //build create course
    @PostMapping("/course")
    public RestResponse saveCourse(@RequestBody CourseRequest courseRequest){
        Course course = courseService.createCourse(courseRequest);
        return new RestResponse(course,"Course created successfully",ResponseStatus.SUCCESS,201);
    }
    //build Update course
    @PutMapping("/course/{id}")
    public RestResponse updateCourse(@RequestBody CourseRequest courseRequest, @PathVariable("id") Long id) throws ResourceNotFoundException{
        try{
            Course course = courseService.updateCourse(courseRequest,id);
            return new RestResponse(course,"Course updated successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build delete course
    @DeleteMapping("/course/{id}")
    public RestResponse deleteCourse(@PathVariable("id") Long id) throws ResourceNotFoundException{
        try{
            courseService.deleteCourse(id);
            return new RestResponse("Course deleted successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build Get one Course by id
    @GetMapping("/course/{id}")
    public RestResponse getCourseById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        try{
            Course course = courseService.getCourseById(id);
            return new RestResponse(course,"Course found successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build get all Course
    @GetMapping("/courses")
    public RestResponse getAllCourses(){
        List<Course> courseList = courseService.getAllCourses();
        return new RestResponse(courseList,"Course list successfully",ResponseStatus.SUCCESS,200);
    }

    /*=======================================================================*/
    /*                          CONSEIL API                                  */
    /*=======================================================================*/
    //Build Create conseil REST API
    @PostMapping("/conseil")
    public RestResponse createConseil(@RequestBody Conseil conseilRequest){
        Conseil conseil = courseService.createConseil(conseilRequest);
        return new RestResponse(conseil,"Conseil Created successfully",ResponseStatus.SUCCESS,201);
    }
    //Build Update Conseil REST API
    @PutMapping("/conseil/{id}")
    public RestResponse updateConseil(@RequestBody Conseil conseilRequest, @PathVariable("id") Long id){
        try{
            Conseil conseil = courseService.updateConseil(conseilRequest, id);
            return new RestResponse(conseil,"Conseil Updated successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build delete Conseil REST API
    @DeleteMapping("/conseil/{id}")
    public RestResponse deleteConseil(@PathVariable("id") Long id){
        try{
            courseService.deleteConseil(id);
            return new RestResponse("Conseil Delete successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build Get Conseil by Id REST API
    @GetMapping("/conseil/{id}")
    public RestResponse getConseilById(@PathVariable("id") Long id){
        try{
            Conseil conseil = courseService.getConseilById(id);
            return new RestResponse(conseil,"Conseil found successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build Get All Conseil REST API
    @GetMapping("/conseils")
    public RestResponse getAllConseils(){
        List<Conseil> conseilList = courseService.getAllConseils();
        return new RestResponse(conseilList,"Conseils displayed all successfully",ResponseStatus.SUCCESS,200);
    }
}
