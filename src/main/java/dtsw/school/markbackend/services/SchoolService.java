package dtsw.school.markbackend.services;

import dtsw.school.markbackend.models.School;
import dtsw.school.markbackend.payload.request.SchoolRequest;
import org.springframework.stereotype.Service;

@Service
public interface SchoolService {
    School saveSchool(SchoolRequest schoolRequest);
}
