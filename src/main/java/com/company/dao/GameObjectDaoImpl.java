package com.company.dao;

import com.company.model.GameObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GameObjectDaoImpl implements GameObjectDao {

    private SessionFactory sessionFactory;

    @Autowired
    public GameObjectDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public GameObject getById(int id) {
        return sessionFactory.getCurrentSession().get(GameObject.class, id);
    }

    @Override
    public void addGameObject(GameObject gameObject) {
        sessionFactory.getCurrentSession().persist(gameObject);
    }

    @Override
    public void updateGameObject(GameObject gameObject) {
        sessionFactory.getCurrentSession().update(gameObject);
    }

    @Override
    public void deleteGameObject(GameObject gameObject) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(gameObject);
        session.flush();
    }
}
