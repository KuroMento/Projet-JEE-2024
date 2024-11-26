package fr.cyu.jee.repository;
import fr.cyu.jee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, String>{
}
