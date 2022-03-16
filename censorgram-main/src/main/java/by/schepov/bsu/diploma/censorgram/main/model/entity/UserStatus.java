package by.schepov.bsu.diploma.censorgram.main.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "user_statuses")
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String description;

    public enum Code {
        ACTIVE, BLOCKED
    }
}
