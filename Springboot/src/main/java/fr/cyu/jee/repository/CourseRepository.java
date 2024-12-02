package fr.cyu.jee.repository;

import fr.cyu.jee.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAll();
    List<Course> findAllByTeacher_Identification(String identification);

    void deleteCourseByIdentification(Long identification);
    Course findCourseByIdentification(Long identification);
}
