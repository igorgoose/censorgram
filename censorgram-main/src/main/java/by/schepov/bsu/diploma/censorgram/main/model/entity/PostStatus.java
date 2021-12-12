package by.schepov.bsu.diploma.censorgram.main.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post_statuses")
public class PostStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String description;
    private String color;

    public enum Code {
        ACTIVE, SUSPICIOUS, BLOCKED
    }
}
