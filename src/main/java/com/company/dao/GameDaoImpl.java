package com.company.dao;

import com.company.model.Game;
import com.company.model.GameObject;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class GameDaoImpl implements GameDao {

    private SessionFactory sessionFactory;

    @Autowired
    public GameDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Game getById(int id) {
        return sessionFactory.getCurrentSession().get(Game.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<GameObject> getGameObjectsByGameId(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from GameObject where game.id =" + id)
                .list();
    }
}
