package dtsw.school.markbackend.controllers;

import dtsw.school.markbackend.exceptions.ResourceNotFoundException;
import dtsw.school.markbackend.models.Classe;
import dtsw.school.markbackend.payload.request.ClasseRequest;
import dtsw.school.markbackend.payload.response.ResponseStatus;
import dtsw.school.markbackend.payload.response.RestResponse;
import dtsw.school.markbackend.services.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/manager")
@RestController
public class ManagerController {
    @Autowired
    private ClasseService classeService;

    @PostMapping("/classe")
    public RestResponse saveClasse(@RequestBody ClasseRequest classeRequest){
        Classe classe = classeService.createClasse(classeRequest);
        return new RestResponse(classe,"Classe Created successfully", ResponseStatus.SUCCESS,201);
    }
    @PutMapping("/classe/{id}")
    public RestResponse updateClasse(@PathVariable("id") Long id, @RequestBody ClasseRequest classeRequest) throws ResourceNotFoundException {
        try{
            Classe classe = classeService.updateClasse(classeRequest,id);
            return new RestResponse(classe,"Classe updated successfully",ResponseStatus.SUCCESS,204);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    @DeleteMapping("/classe/{id}")
    public RestResponse deleteClasse(@PathVariable("id") Long id) throws ResourceNotFoundException{
        try{
            classeService.deleteClasse(id);
            return new RestResponse("Classe deleted successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,400);
        }
    }
    @GetMapping("/classe/{id}")
    public RestResponse getClasseById(@PathVariable("id") Long id) throws ResourceNotFoundException{
        try{
            Classe classe = classeService.getClasseById(id);
            return new RestResponse(classe,"Classe found successfully",ResponseStatus.SUCCESS,200);
        }catch (ResourceNotFoundException e){
            return new RestResponse(e.getMessage(),ResponseStatus.FAILED,404);
        }
    }
    @GetMapping("/classes")
    public RestResponse getAllClasses(){
        List<Classe> classeList = classeService.getAllClasses();
        return new RestResponse(classeList,"Classes list successfully",ResponseStatus.SUCCESS,200);
    }
}
