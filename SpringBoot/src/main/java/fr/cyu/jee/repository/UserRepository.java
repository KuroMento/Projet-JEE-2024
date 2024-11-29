package fr.cyu.jee.repository;
import fr.cyu.jee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    User findUserByIdentification(String identifiant);
}
