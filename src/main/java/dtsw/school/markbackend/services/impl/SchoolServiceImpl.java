package dtsw.school.markbackend.services.impl;

import dtsw.school.markbackend.exceptions.ResourceNotFoundException;
import dtsw.school.markbackend.models.School;
import dtsw.school.markbackend.payload.request.SchoolRequest;
import dtsw.school.markbackend.repository.SchoolDAO;
import dtsw.school.markbackend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {
    private SchoolDAO schoolDAO;

    public SchoolServiceImpl(SchoolDAO schoolDAO) {
        super();
        this.schoolDAO = schoolDAO;
    }

    @Override
    public School saveSchool(SchoolRequest schoolRequest) {
        School school= new School(schoolRequest.getName(), schoolRequest.getEmail(),schoolRequest.getDivision(),schoolRequest.getSubdivision());
        return schoolDAO.save(school);
    }

    @Override
    public School updateSchool(SchoolRequest schoolRequest, Long id) {
        //we need to check whether school with given id is exist in BD or not
        School existingSchool = schoolDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("School","Id",id));
        existingSchool.setName(schoolRequest.getName());
        existingSchool.setEmail(schoolRequest.getEmail());
        existingSchool.setDivision(schoolRequest.getDivision());
        existingSchool.setSubdivision(schoolRequest.getSubdivision());
        existingSchool = schoolDAO.save(existingSchool);
        return existingSchool;
    }

    @Override
    public void deleteSchool(Long id) {
        School existingSchool = schoolDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("School","Id",id));
        schoolDAO.deleteById(existingSchool.getId());
    }

    @Override
    public School getSchoolById(long id) {
        /*Optional<School> school = schoolDAO.findById(id);
        if(school.isPresent()){
            return school.get();
        }
        else{
            throw new ResourceNotFoundException("School","Id",id);
        }*/
        return schoolDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("School","Id",id));
    }

    @Override
    public List<School> getAllSchool() {
        return schoolDAO.findAll();
    }

}
