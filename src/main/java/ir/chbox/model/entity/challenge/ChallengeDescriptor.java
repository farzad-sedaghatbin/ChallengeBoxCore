package ir.chbox.model.entity.challenge;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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

    public boolean isPrivateChallenge() {
        return privateChallenge;
    }

    public void setPrivateChallenge(boolean privateChallenge) {
        this.privateChallenge = privateChallenge;
    }
}
