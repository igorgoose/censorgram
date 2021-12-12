package by.schepov.bsu.diploma.censorgram.main.repository;

import by.schepov.bsu.diploma.censorgram.main.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "from User u "
            + "join fetch u.role r "
            + "join fetch r.authorities")
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
