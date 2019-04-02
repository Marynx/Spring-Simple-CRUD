package pl.marcin.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.marcin.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
