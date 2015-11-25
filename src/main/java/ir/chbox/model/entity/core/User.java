package ir.chbox.model.entity.core;

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
                query = "select u from User u where u.username=:username and u.password=:password and u.deleted='0'"
        ),
        @NamedQuery(
                name = "User.findById",
                query = "select u from User u where u.id=:id" ),
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
    private boolean enable=true;

    @Column(name = "DELETED")
    protected boolean deleted=false;

    @Column(name = "STATUS")
    protected String status="ACTIVE";

    @Column(name = "failedLoginCount")
    private String failedLoginCount;

    @Column(name = "lastLoginDate")
    private String lastLoginDate;

    @Column(name = "lastLoginIP")
    private String lastLoginIP;

    private String mobileNumber;

    private String Locale;

    private String email;

    private long picture;

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> freinds;




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
        return Locale;
    }

    public void setLocale(String locale) {
        Locale = locale;
    }

    public long getPicture() {
        return picture;
    }

    public void setPicture(long picture) {
        this.picture = picture;
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
}
