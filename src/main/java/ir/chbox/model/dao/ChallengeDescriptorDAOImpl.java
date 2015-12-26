package ir.chbox.model.dao;

import ir.chbox.model.entity.challenge.ChallengeDescriptor;

import java.util.List;

/**
 * Created by farzad on 12/25/2015.
 */
public class ChallengeDescriptorDAOImpl<T extends ChallengeDescriptor> extends BaseDAOImpl {
    public List<ChallengeDescriptor> topChallenge(){
        return em.createNamedQuery("ChallengeDescriptor.top").getResultList();
    }
}
