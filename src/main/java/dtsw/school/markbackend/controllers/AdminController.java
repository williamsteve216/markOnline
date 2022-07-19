package dtsw.school.markbackend.controllers;

import dtsw.school.markbackend.exceptions.ResourceNotFoundException;
import dtsw.school.markbackend.models.School;
import dtsw.school.markbackend.payload.request.SchoolRequest;
import dtsw.school.markbackend.payload.response.ResponseStatus;
import dtsw.school.markbackend.payload.response.RestResponse;
import dtsw.school.markbackend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin")
@RestController
public class AdminController {
    @Autowired
    private SchoolService schoolService;

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
}
