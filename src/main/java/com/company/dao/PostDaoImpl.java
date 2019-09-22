package com.company.dao;

import com.company.model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PostDaoImpl implements PostDao {

    private SessionFactory sessionFactory;

    @Autowired
    public PostDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getAllPosts() {
        return sessionFactory.getCurrentSession().createQuery("from Post").list();
    }

    @Override
    public void savePost(Post post) {
        sessionFactory.getCurrentSession().persist(post);
    }

    @Override
    public void deletePost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(post);
        session.flush();
    }

    @Override
    public void updatePost(Post post) {
        sessionFactory.getCurrentSession().update(post);
    }

    @Override
    public Post getById(int id) {
        return sessionFactory.getCurrentSession().get(Post.class, id);
    }
}
