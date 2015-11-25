package ir.chbox.model.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class BaseDAOImpl<T> {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("chbox");
    protected EntityManager em = emf.createEntityManager();

    public void update(T entity) {
        try {
            em.getTransaction().begin();
           em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(T entity) {
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(T entity) {
        em.getTransaction().begin();
        System.out.println("sdfsdf");
        em.persist(entity);
        em.getTransaction().commit();
    }

    public T find(T entity, Object primaryKey) {
        T t=null;
        try {
            em.getTransaction().begin();
            t= (T) em.find(entity.getClass(), primaryKey);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


}
