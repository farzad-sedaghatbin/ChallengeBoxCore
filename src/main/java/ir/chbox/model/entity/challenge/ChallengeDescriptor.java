package ir.chbox.model.entity.challenge;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by farzad on 8/22/15.
 */
@NamedQueries({
        @NamedQuery(
                name = "ChallengeDescriptor.top",
                query = "select c from ChallengeDescriptor c "
        )
})
@Entity
@Table
public class ChallengeDescriptor extends ChallengeBase {

    private boolean privateChallenge = false;

    @Column(name = "description")
    private String description;
    @ManyToMany
    private Set<CategoryChallenge> categoryChallenges;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CategoryChallenge> getCategoryChallenges() {
        return categoryChallenges;
    }

    public void setCategoryChallenges(Set<CategoryChallenge> categoryChallenges) {
        this.categoryChallenges = categoryChallenges;
    }

    public boolean isPrivateChallenge() {
        return privateChallenge;
    }

    public void setPrivateChallenge(boolean privateChallenge) {
        this.privateChallenge = privateChallenge;
    }
}
