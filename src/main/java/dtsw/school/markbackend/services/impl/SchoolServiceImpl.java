package dtsw.school.markbackend.services.impl;

import dtsw.school.markbackend.models.School;
import dtsw.school.markbackend.payload.request.SchoolRequest;
import dtsw.school.markbackend.repository.SchoolDAO;
import dtsw.school.markbackend.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
