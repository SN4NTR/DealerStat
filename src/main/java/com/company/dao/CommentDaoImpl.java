package com.company.dao;

import com.company.model.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CommentDaoImpl implements CommentDao {

    private SessionFactory sessionFactory;

    @Autowired
    public CommentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Comment getById(int id) {
        return sessionFactory.getCurrentSession().get(Comment.class, id);
    }

    @Override
    public void saveComment(Comment comment) {
        sessionFactory.getCurrentSession().persist(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(comment);
        session.flush();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comment> getAllComments() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Comment")
                .list();
    }

    @Override
    public void updateComment(Comment comment) {
        sessionFactory.getCurrentSession().update(comment);
    }
}
