package ir.chbox.model.entity.challenge;

import ir.chbox.model.entity.core.Comment;
import ir.chbox.model.entity.core.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

/**
 * Created by farzad on 8/22/15.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ChallengeBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    protected long id;

    @Column(name = "title")
    protected String title;
    @Column
    protected float rating;
    @Column
    protected LocalTime createTime;
    @Column
    protected LocalTime expireTime;
    @Column
    protected String stream;
    @Column
    protected String thumbnail;
    @Column
    protected String mediaType;

    @OneToMany
    protected Set<Comment> comments;
    @OneToOne
    protected User user;
    @ManyToOne
    protected CategoryChallenge categoryChallenge;
    @Transient
    protected String type;
    @Transient
    protected String userId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public float getRating() {
        return rating;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CategoryChallenge getCategory() {
        return categoryChallenge;
    }

    public void setCategory(CategoryChallenge category) {
        this.categoryChallenge = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CategoryChallenge getCategoryChallenge() {
        return categoryChallenge;
    }

    public void setCategoryChallenge(CategoryChallenge categoryChallenge) {
        this.categoryChallenge = categoryChallenge;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public LocalTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalTime createTime) {
        this.createTime = createTime;
    }

    public LocalTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalTime expireTime) {
        this.expireTime = expireTime;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
