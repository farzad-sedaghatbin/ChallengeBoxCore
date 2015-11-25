package ir.chbox.model.dao;



import ir.chbox.model.entity.core.User;

import java.util.List;


public class UserDAOImpl extends BaseDAOImpl<User> {


    public boolean exist(String username) {
        try {
            List<User> user = (List<User>) em.createNamedQuery("User.exist")
                    .setParameter("username", username)
                    .setParameter("deleted", "0")
                    .getResultList();
            if (user.size() != 0)
                return true;
            else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


    public User authenticate(String username, String password) {
        try {
            return (User) em.createNamedQuery("User.findByUsernameAndPassword")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


    public User findById(String id) {
        try {
            return (User) em.createNamedQuery("User.findById")
                    .setParameter("id", Long.valueOf(id))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


    public User findByUsername(String username) {
        try {
            return (User) em.createNamedQuery("User.findByUsername")
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }







}