package dtsw.school.markbackend.repository;

import dtsw.school.markbackend.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolDAO extends JpaRepository<School,Long> {
    School save(School school);
}
