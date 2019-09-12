package com.company.dao;

import com.company.model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDaoImpl implements PostDao {

    private SessionFactory sessionFactory;

    @Autowired
    public PostDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getAllPosts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Post").list();
    }

    @Override
    public void addPost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(post);
    }

    @Override
    public void deletePost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(post);
        session.flush();
    }

    @Override
    public void updatePost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.update(post);
    }

    @Override
    public Post getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Post.class, id);
    }
}
