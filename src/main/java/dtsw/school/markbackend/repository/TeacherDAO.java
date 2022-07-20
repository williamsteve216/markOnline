package dtsw.school.markbackend.repository;

import dtsw.school.markbackend.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDAO extends JpaRepository<Teacher,Long> {
}
