package dtsw.school.markbackend.repository;

import dtsw.school.markbackend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDAO extends JpaRepository<Course, Long> {
}
