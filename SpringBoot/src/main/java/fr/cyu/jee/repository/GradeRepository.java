package fr.cyu.jee.repository;

import fr.cyu.jee.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, String> {
}
