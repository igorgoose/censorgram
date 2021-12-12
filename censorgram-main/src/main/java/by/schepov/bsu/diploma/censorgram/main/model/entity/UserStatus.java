package by.schepov.bsu.diploma.censorgram.main.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
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
