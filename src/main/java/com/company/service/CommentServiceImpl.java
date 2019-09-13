package com.company.service;

import com.company.dao.CommentDao;
import com.company.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public Comment getById(int id) {
        return commentDao.getById(id);
    }

    @Override
    public void addComment(Comment comment) {
        comment.setCreatedAt(new Date(new java.util.Date().getTime()));
        commentDao.addComment(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentDao.deleteComment(comment);
    }
}
