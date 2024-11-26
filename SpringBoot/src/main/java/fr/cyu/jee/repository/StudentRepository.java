package fr.cyu.jee.repository;

import fr.cyu.jee.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
