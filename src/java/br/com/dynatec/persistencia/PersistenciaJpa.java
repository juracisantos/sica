package br.com.dynatec.persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

public abstract class PersistenciaJpa implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static EntityManagerFactory emf;
    private EntityManager em;

    static {
        emf = Persistence.createEntityManagerFactory("sicePU");
    }

    protected PersistenciaJpa() {
        em = emf.createEntityManager();
    }

    protected <T> T save(Class<T> beanCast, Object bean) throws Exception {
        this.em.getTransaction().begin();
        try {
            this.em.persist(bean);
            this.em.getTransaction().commit();
            return (T) bean;
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    protected <T> T remove(Class<T> beanCast, Object bean) throws Exception {
        this.em.getTransaction().begin();
        try {
            this.em.remove(bean);
            this.em.getTransaction().commit();
            return (T) bean;
        } catch (Exception e) {
            this.em.getTransaction().rollback();
            throw new Exception(e.getMessage());
        }
    }

    /**
     *
     * @param query
     * @param values
     * @return
     */
    protected List findAll(String query, Object... values) {
        Query qr = createQuery(query, false, values);
        return qr.getResultList();
    }
    
    protected <T> List<T> findAll(Class<T> classToCast, String query, Object... values) {
        Query qr = createQuery(query, false, values);
        return qr.getResultList();
    }

    protected <T> List<T> findLimitedList(Class<T> classToCast, String query, int limit, Object... values) {
        Query qr = createQuery(query, false, values);
        qr.setMaxResults(limit);
        return qr.getResultList();
    }

    protected <T> T first(Class<T> classToCast, String query, Object... values) {
        List<T> registros = findLimitedList(classToCast, query, 1, values);

        return registros.isEmpty() ? null : registros.get(0);
    }

    protected <T> T find(Class<T> classToCast, String query, Object... values) {
        Query qr = createQuery(query, false, values);
        try {
            return (T) qr.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    protected <T> T find(Class<T> classToCast, Serializable primaryKey) {
        return em.find(classToCast, primaryKey);
    }

    protected <T> T executeNativeQuery(String qry, Object... values) {
        Query qr = createQuery(qry, true, values);
        return (T) qr.getSingleResult();
    }

    protected <T> T executeQuery(String query, Object... values) {
        Query qr = createQuery(query, false, values);
        return (T) qr.getSingleResult();
    }

    protected int execute(String query, Object... values) {
        Query qr = createQuery(query, false, values);
        return qr.executeUpdate();
    }

    protected Query createQuery(String query, boolean nativeQuery, Object[] values) {
        Query qr;
        qr = nativeQuery ? em.createNativeQuery(query) : em.createQuery(query);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                Object object = values[i];
                qr.setParameter(i + 1, object);
            }
        }
        return qr;
    }

    private EntityManager getEm() {
        return em;
    }

    private void setEm(EntityManager em) {
        this.em = em;
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }

}
