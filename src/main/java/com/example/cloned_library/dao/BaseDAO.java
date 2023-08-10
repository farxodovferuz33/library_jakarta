package com.example.cloned_library.dao;

import com.example.cloned_library.entity.Auditable;
import com.example.cloned_library.entity.Book;
import jakarta.persistence.*;
import lombok.NonNull;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDAO<T extends Auditable, ID extends Serializable> {
    protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
    protected static final EntityManager em = emf.createEntityManager();

    private final Class<T> persistenceClass;

    @SuppressWarnings("unchecked")
    protected BaseDAO() {
        this.persistenceClass = (Class<T>) (((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]);
    }


    public T save(T entity) {
        begin();
        em.persist(entity);
        commit();
        return entity;
    }

    public boolean update(T entity) {
        begin();
        em.merge(entity);
        commit();
        return true;
    }

    public T findById(@NonNull ID id) {
        T entity = em.find(persistenceClass, id);
        return entity;
    }


    public List<Book> getEntitiesByPage(int pageSize, int pageNumber) {
        begin();

        TypedQuery<Book> selectBFromBookB = em.createQuery("select b from Book b", Book.class);
        selectBFromBookB.setMaxResults(pageSize);
        selectBFromBookB.setFirstResult((pageNumber - 1 )*pageSize);
        List<Book> entities = selectBFromBookB.getResultList();

        commit();
        return entities;
    }

    public boolean deleteById(@NonNull ID id) {
        begin();
        em.createQuery("delete from " + persistenceClass.getSimpleName() + " t where t.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        commit();
        return true;
    }


    protected void begin() {
        em.getTransaction().begin();
    }

    protected void commit() {
        em.getTransaction().commit();
    }

    public void rollBack() {
        em.getTransaction().rollback();
    }

    public long getTotalRecords() {
        TypedQuery<Long> query = em.createQuery("select count(b) from Book b ", Long.class);
        return query.getSingleResult();
    }
}
