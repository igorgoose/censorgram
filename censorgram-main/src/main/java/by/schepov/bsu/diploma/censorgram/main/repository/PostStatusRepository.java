package by.schepov.bsu.diploma.censorgram.main.repository;

import by.schepov.bsu.diploma.censorgram.main.model.entity.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostStatusRepository extends JpaRepository<PostStatus, Long> {
    PostStatus findByCode(String code);
}
