package com.company.service;

import com.company.dao.CommentDao;
import com.company.model.Comment;
import com.company.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao;
    private UserService userService;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao, UserService userService) {
        this.commentDao = commentDao;
        this.userService = userService;
    }

    @Override
    public Comment getById(int id) {
        return commentDao.getById(id);
    }

    @Override
    public void saveComment(Comment comment) {
        User user = userService.getById(UserServiceImpl.userIdBuffer);

        comment.setUser(user);
        comment.setCreatedAt(new Date(new java.util.Date().getTime()));
        comment.setApproved(false);

        Set<Comment> comments = new HashSet<>();
        comments.add(comment);

        user.setComments(comments);

        commentDao.saveComment(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentDao.deleteComment(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        comment.setApproved(true);
        commentDao.updateComment(comment);
    }

    @Override
    public List<Comment> getNotApprovedComments() {
        return commentDao.getAllComments()
                .stream()
                .filter(comment -> !comment.isApproved())
                .collect(Collectors.toList());
    }
}
