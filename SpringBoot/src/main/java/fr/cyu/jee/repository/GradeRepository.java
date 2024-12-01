package fr.cyu.jee.repository;

import fr.cyu.jee.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAll();
    List<Grade> findAllByStudent_Identification(String identification);
    List<Grade> findAllByCourse_TeacherIdentificationContaining(String identification);
}
