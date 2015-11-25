package ir.chbox.model.entity.core;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by farzad on 8/22/15.
 */
@Entity
@Table(name ="Comment" )
public  class Comment implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    private String comment;

    private int like;

    private int unLike;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getUnLike() {
        return unLike;
    }

    public void setUnLike(int unLike) {
        this.unLike = unLike;
    }
}