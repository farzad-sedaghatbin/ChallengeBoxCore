package ir.chbox.model.service;

import ir.chbox.model.dao.ChallengeDescriptorDAOImpl;
import ir.chbox.model.entity.challenge.ChallengeDescriptor;

import java.util.List;

/**
 * Created by farzad on 12/25/2015.
 */
public class ChallengeDescriptorService {
    private static final ChallengeDescriptorService challengeDescriptorService = new ChallengeDescriptorService();


    public static ChallengeDescriptorService getInstance() {
        return challengeDescriptorService;
    }

    public void create(ChallengeDescriptor challengeDescriptor) {
        new ChallengeDescriptorDAOImpl<>().create(challengeDescriptor);
    }

    public List<ChallengeDescriptor> getTop() {
        return new ChallengeDescriptorDAOImpl<>().topChallenge();
    }
}
