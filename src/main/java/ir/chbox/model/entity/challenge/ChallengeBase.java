package ir.chbox.model.entity.challenge;

import ir.chbox.model.entity.core.Comment;
import ir.chbox.model.entity.core.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by farzad on 8/22/15.
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class ChallengeBase implements Serializable{
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        @Column(name = "id")
        protected long id;

        @Column(name = "title")
        protected String title;

        @Column(name = "description")
        protected String description;

        protected String rating;

        protected long stream;

        @OneToMany
        protected Set<Comment> comments;
        @OneToOne
        protected User user;


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

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public long getStream() {
                return stream;
        }

        public void setStream(long stream) {
                this.stream = stream;
        }

        public String getRating() {
                return rating;
        }

        public void setRating(String rating) {
                this.rating = rating;
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
}
