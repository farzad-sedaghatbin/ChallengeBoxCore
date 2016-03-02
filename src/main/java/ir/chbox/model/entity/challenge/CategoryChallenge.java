package ir.chbox.model.entity.challenge;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by farzad on 8/22/15.
 */
@Entity
@Table
public class CategoryChallenge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String id;
    @Column
    private String title;
    @Column
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
