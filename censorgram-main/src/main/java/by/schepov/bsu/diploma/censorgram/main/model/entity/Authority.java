package by.schepov.bsu.diploma.censorgram.main.model.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String description;

    @Override
    public String getAuthority() {
        return code;
    }

    public enum Code {
        POSTS_VIEW, POSTS_EDIT, ANALYTICS_VIEW, USERS_VIEW
    }
}
