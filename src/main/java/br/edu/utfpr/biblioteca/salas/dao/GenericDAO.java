/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author a1602063
 */
public abstract class GenericDAO<T> {

    public static EntityManager entityManager = Persistence.createEntityManagerFactory("UP").createEntityManager();

    private Class clazz;

    public GenericDAO(Class clazz) {
        this.clazz = clazz;
    }

    public void insert(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public void update(T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    public void delete(T entity) {
        if (entity != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        }
    }

    public T obter(int id) {
        entityManager.clear();
        return (T) entityManager.find(clazz, id);
    }
    
    public T obter(T entity) {
        entityManager.clear();
        return (T) entityManager.find(clazz, entity);
    }

    public List<T> list() {
        System.out.println("SELECT e FROM " + clazz.getSimpleName() + " e");
        return entityManager.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e").getResultList();
    }

}
