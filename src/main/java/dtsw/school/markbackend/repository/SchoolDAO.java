package dtsw.school.markbackend.repository;

import dtsw.school.markbackend.models.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface SchoolDAO extends JpaRepository<School,Long> {
    School save(School school);

}
