package dtsw.school.markbackend.controllers;

import dtsw.school.markbackend.models.School;
import dtsw.school.markbackend.payload.request.SchoolRequest;
import dtsw.school.markbackend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private SchoolService schoolService;

    //build create school REST API
    @PostMapping("/school")
    public ResponseEntity<School> saveSchool(@RequestBody SchoolRequest schoolRequest){
        School schoolResult = schoolService.saveSchool(schoolRequest);
        return new ResponseEntity<School>(schoolResult, HttpStatus.CREATED);
    }
}
