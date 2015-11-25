package ir.chbox.model.entity.core;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by farzad on 8/22/15.
 */
@Entity
@Table(name ="Notification" )
public class Notification implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    private String description;

    private boolean read=false;

    @ManyToOne
    private User user;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
