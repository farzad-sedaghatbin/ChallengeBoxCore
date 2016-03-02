package ir.chbox.model.entity.core;

import ir.chbox.model.entity.challenge.ChallengeDescriptor;
import ir.chbox.model.entity.challenge.ChallengeInstance;
import ir.chbox.model.entity.core.Notification;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by farzad on 8/20/15.
 */
@Entity
@Table(name = "tb_user")
@NamedQueries({
        @NamedQuery(
                name = "User.findByUsernameAndPassword",
                query = "select u from User u where u.username=:username and u.password=:password and u.deleted=false "
        ),
        @NamedQuery(
                name = "User.findById",
                query = "select u from User u where u.id=:id"),
        @NamedQuery(
                name = "User.exist",
                query = "select u from User u where u.username=:username and u.deleted=:deleted"),
        @NamedQuery(
                name = "User.findByUsername",
                query = "select u from User u where u.username=:username"
        )})
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enable")
    private boolean enable = true;

    @Column(name = "DELETED")
    protected boolean deleted = false;

    @Column(name = "STATUS")
    protected String status = "ACTIVE";

    @Column(name = "failedLoginCount")
    private String failedLoginCount;

    @Column(name = "lastLoginDate")
    private String lastLoginDate;

    @Column(name = "lastLoginIP")
    private String lastLoginIP;
    @Column
    private String mobileNumber;
    @Column
    private String locale;
    @Column
    private String location;
    @Column
    private String email;
    @Column
    private float rating;
    @Column
    private String mobile;
    @Column
    private String activationCode;
    @Column
    private String passwordStatus;
    @Column
    private boolean activated = false;
    @Lob
    @Column
    private byte[] picture;

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> freinds;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> pendingUser;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<ChallengeInstance> doChallenge;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<ChallengeDescriptor> followChallenge;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<ChallengeDescriptor> createChallenge;


    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFailedLoginCount() {
        return failedLoginCount;
    }

    public void setFailedLoginCount(String failedLoginCount) {
        this.failedLoginCount = failedLoginCount;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public long getId() {
        return id;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        locale = locale;
    }


    public Set<User> getFreinds() {
        return freinds;
    }

    public void setFreinds(Set<User> freinds) {
        this.freinds = freinds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPasswordStatus() {
        return passwordStatus;
    }

    public void setPasswordStatus(String passwordStatus) {
        this.passwordStatus = passwordStatus;
    }

    public Set<User> getPendingUser() {
        return pendingUser;
    }

    public void setPendingUser(Set<User> pendingUser) {
        this.pendingUser = pendingUser;
    }

    public Set<ChallengeInstance> getDoChallenge() {
        return doChallenge;
    }

    public void setDoChallenge(Set<ChallengeInstance> doChallenge) {
        this.doChallenge = doChallenge;
    }

    public Set<ChallengeDescriptor> getFollowChallenge() {
        return followChallenge;
    }

    public void setFollowChallenge(Set<ChallengeDescriptor> followChallenge) {
        this.followChallenge = followChallenge;
    }

    public Set<ChallengeDescriptor> getCreateChallenge() {
        return createChallenge;
    }

    public void setCreateChallenge(Set<ChallengeDescriptor> createChallenge) {
        this.createChallenge = createChallenge;
    }
}
