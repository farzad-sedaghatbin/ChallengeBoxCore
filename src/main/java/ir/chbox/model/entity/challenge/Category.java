package ir.chbox.model.entity.challenge;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by farzad on 8/22/15.
 */
@Entity
@Table
public class Category {

    private String id;

    private String title;
    @OneToMany(mappedBy = "category")
    private Set<ChallengeBase> challengeBases;

    @Id
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

    public Set<ChallengeBase> getChallengeBases() {
        return challengeBases;
    }

    public void setChallengeBases(Set<ChallengeBase> challengeBases) {
        this.challengeBases = challengeBases;
    }
}
