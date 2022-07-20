package dtsw.school.markbackend.repository;

import dtsw.school.markbackend.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDAO extends JpaRepository<Student,Long> {
}
