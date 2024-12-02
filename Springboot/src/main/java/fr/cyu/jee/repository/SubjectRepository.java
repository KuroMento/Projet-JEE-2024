package fr.cyu.jee.repository;

import fr.cyu.jee.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAll();
    void deleteSubjectByIdentification(Long id);
    Subject findSubjectByIdentification(Long identification);
}
