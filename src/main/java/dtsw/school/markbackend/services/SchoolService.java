package dtsw.school.markbackend.services;

import dtsw.school.markbackend.models.School;
import dtsw.school.markbackend.payload.request.SchoolRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SchoolService {
    School saveSchool(SchoolRequest schoolRequest);
    School updateSchool(SchoolRequest schoolRequest, Long id);
    void deleteSchool(Long id);
    School getSchoolById(long id);
    List<School> getAllSchool();
}
