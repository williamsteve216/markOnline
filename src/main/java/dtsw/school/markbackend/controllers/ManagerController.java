package dtsw.school.markbackend.controllers;

import dtsw.school.markbackend.exceptions.ExistException;
import dtsw.school.markbackend.exceptions.ResourceNotFoundException;
import dtsw.school.markbackend.models.*;
import dtsw.school.markbackend.payload.request.*;
import dtsw.school.markbackend.payload.response.ResponseStatus;
import dtsw.school.markbackend.payload.response.RestResponse;
import dtsw.school.markbackend.services.ClasseService;
import dtsw.school.markbackend.services.StudentService;
import dtsw.school.markbackend.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/manager")
@RestController
public class ManagerController {
    @Autowired
    private ClasseService classeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    /*=========================================================================*/
    /*                             CLASSE API                                  */
    /*=========================================================================*/
    //Build create Classe REST API
    @PostMapping("/classe")
    public RestResponse saveClasse(@RequestBody ClasseRequest classeRequest){
        Classe classe = classeService.createClasse(classeRequest);
        System.out.print(classe);
        School school = new School();
        //Classe classe1 = new Classe("Test","2005-2015","BACC",school);
        return new RestResponse(classe,"Classe Created successfully", ResponseStatus.SUCCESS,201);
    }
    //Build Update Classe REST API
    @PutMapping("/classe/{id}")
    public RestResponse updateClasse(@PathVariable("id") Long id, @RequestBody ClasseRequest classeRequest) throws ResourceNotFoundException {
        try{
            Classe classe = classeService.updateClasse(classeRequest,id);
            return new RestResponse(classe,"Classe updated successfully",ResponseStatus.SUCCESS,204);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build Delete classe REST API
    @DeleteMapping("/classe/{id}")
    public RestResponse deleteClasse(@PathVariable("id") Long id) throws ResourceNotFoundException{
        try{
            classeService.deleteClasse(id);
            return new RestResponse("Classe deleted successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,400);
        }
    }
    //Build Get classe by id REST API
    @GetMapping("/classe/{id}")
    public RestResponse getClasseById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        try{
            Classe classe = classeService.getClasseById(id);
            return new RestResponse(classe,"Classe found successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build Get All Classe REST API
    @GetMapping("/classes")
    public RestResponse getAllClasses(){
        List<Classe> classeList = classeService.getAllClasses();
        return new RestResponse(classeList,"Classes list successfully",ResponseStatus.SUCCESS,200);
    }

    //Build add classe course teacher Rest API
    @PostMapping("/classe/addCourseTeacher")
    public RestResponse createClasseCourseTeacher(@RequestBody ClasseCourseTeacherRequest classeCourseTeacherRequest) throws ResourceNotFoundException{
        try {
            List<ClasseCourseTeacher> classeCourseTeachers = classeService.createClasseCourseTeacher(classeCourseTeacherRequest);
            return new RestResponse(classeCourseTeachers,"Course and teacher added successfully to classe",ResponseStatus.SUCCESS,201);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build add Teacher to classe
    @PostMapping("/classe/addTeacher")
    public RestResponse addTeacherToClasse(@RequestBody ClasseCourseTeacherRequest classeCourseTeacherRequest){
        try{
            List<ClasseCourseTeacher> classeCourseTeachers = classeService.addTeacherToClasse(classeCourseTeacherRequest);
            return new RestResponse(classeCourseTeachers,"Teacher added to the classe successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build remove Teacher to classe
    @PostMapping("/classe/removeTeacher")
    public RestResponse removeTeacherToClasse(@RequestBody ClasseCourseTeacherRequest classeCourseTeacherRequest){
        try{
            List<ClasseCourseTeacher> classeCourseTeachers = classeService.removeTeacherToClasse(classeCourseTeacherRequest);
            return new RestResponse(classeCourseTeachers,"The teacher has remove successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build add Student to classe
    @PostMapping("/classe/addStudent")
    public RestResponse addStudentToClasse(@RequestBody ClasseStudentRequest classeStudentRequest){
        try{
            List<ClasseStudent> classeStudents = classeService.enrollStudentToClasse(classeStudentRequest);
            return new RestResponse(classeStudents,"The student enroll successfully to classe",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
        catch (ExistException e){
            return new RestResponse(e.getMessage(),ResponseStatus.ABORTED,400);
        }
    }
    //Build remove student to classe
    @PostMapping("/classe/removeStudent")
    public RestResponse removeStudentToClasse(ClasseStudentRequest classeStudentRequest){
        try{
            List<ClasseStudent> classeStudents = classeService.removeStudentToClasse(classeStudentRequest);
            return new RestResponse(classeStudents,"The student remove to classe successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.ABORTED,400);
        }
    }

    /*=========================================================================*/
    /*                             STUDENT API                                 */
    /*=========================================================================*/
    //Build Create Student REST API
    @PostMapping("/student")
    public RestResponse createStudent(@RequestBody StudentRequest studentRequest){
        Student student = studentService.createStudent(studentRequest);
        return new RestResponse(student,"Student Created successfully",ResponseStatus.SUCCESS,201);
    }
    //Build Update Student REST API
    @PutMapping("/student/{id}")
    public RestResponse updateStudent(@PathVariable("id") Long id, @RequestBody StudentRequest studentRequest){
        try{
            Student student = studentService.updateStudent(studentRequest,id);
            return new RestResponse(student,"Student Updated successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build Delete Student REST API
    @DeleteMapping("/student/{id}")
    public RestResponse deleteStudent(@PathVariable("id") Long id) {
        try{
            studentService.deleteStudent(id);
            return new RestResponse("Student Deleted Successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build Get Student By Id REST API
    @GetMapping("/student/{id}")
    public RestResponse getStudentById(@PathVariable("id") Long id){
        try{
            Student student = studentService.getStudentById(id);
            return new RestResponse(student,"Student found successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build List All Student REST API
    @GetMapping("/students")
    public RestResponse getAllStudents(){
        List<Student> studentList = studentService.getAllStudents();
        return new RestResponse(studentList,"Students display successfully",ResponseStatus.SUCCESS,200);
    }

    /*=========================================================================*/
    /*                             TEACHER API                                 */
    /*=========================================================================*/
    //Build Create Teacher REST API
    @PostMapping("/teacher")
    public RestResponse createTeacher(@RequestBody TeacherRequest teacherRequest){
        Teacher teacher = teacherService.createTeacher(teacherRequest);
        return new RestResponse(teacher,"Teacher created successfully",ResponseStatus.SUCCESS,201);
    }
    //Build Update Teacher REST API
    @PutMapping("/teacher/{id}")
    public RestResponse updateTeacher(@RequestBody TeacherRequest teacherRequest, @PathVariable("id") Long id){
        try{
            Teacher teacher = teacherService.updateTeacher(teacherRequest, id);
            return new RestResponse(teacher,"Teacher updated successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build Delete Teacher REST API
    @DeleteMapping("/teacher/{id}")
    public RestResponse deleteTeacher(@PathVariable("id") Long id){
        try{
            teacherService.deleteTeacher(id);
            return new RestResponse("Teacher deleted successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build Get Teacher By Id REST API
    @GetMapping("/teacher/{id}")
    public RestResponse getTeacherById(@PathVariable("id") Long id){
        try{
            Teacher teacher = teacherService.getTeacherById(id);
            return new RestResponse(teacher,"Teacher found successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    //Build Get All Teacher REST API
    @GetMapping("/teachers")
    public RestResponse getAllTeachers(){
        List<Teacher>teacherList = teacherService.getAllTeachers();
        return new RestResponse(teacherList,"Teachers display all Successfuly",ResponseStatus.SUCCESS,200);
    }
}
