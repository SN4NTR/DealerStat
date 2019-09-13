package com.company.dao;

import com.company.model.GameObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GameObjectDaoImpl implements GameObjectDao {

    private SessionFactory sessionFactory;

    @Autowired
    public GameObjectDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public GameObject getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(GameObject.class, id);
    }

    @Override
    public void addGameObject(GameObject gameObject) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(gameObject);
    }

    @Override
    public void updateGameObject(GameObject gameObject) {
        Session session = sessionFactory.getCurrentSession();
        session.update(gameObject);
    }

    @Override
    public void deleteGameObject(GameObject gameObject) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(gameObject);
        session.flush();
    }
}
