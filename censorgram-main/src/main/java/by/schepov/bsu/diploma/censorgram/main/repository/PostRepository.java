package by.schepov.bsu.diploma.censorgram.main.repository;

import by.schepov.bsu.diploma.censorgram.main.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserIdOrderByUpdatedDateDescCreatedDateDesc(Long userId);

    Page<Post> findByUserIdOrderByUpdatedDateDescCreatedDateDesc(Long userId, Pageable pageable);
}
