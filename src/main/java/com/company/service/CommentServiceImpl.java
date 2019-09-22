package com.company.service;

import com.company.dao.CommentDao;
import com.company.dao.UserDao;
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
    private UserDao userDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao, UserService userService, UserDao userDao) {
        this.commentDao = commentDao;
        this.userService = userService;
        this.userDao = userDao;
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

    private double rating(int id) {
        Set<Comment> comments = userService.getById(id).getComments();

        double result = 0;
        for (Comment c : comments) {
            result += c.getRating();
        }

        return comments.size() > 0 ? result / comments.size() : 0;
    }

    @Override
    public void deleteComment(Comment comment) {
        int userId = comment.getUser().getId();

        commentDao.deleteComment(comment);

        User user = userService.getById(userId);
        user.setRating(rating(user.getId()));
        userDao.updateUser(user);
    }

    @Override
    public void updateComment(Comment comment) {
        comment.setApproved(true);
        commentDao.updateComment(comment);

        int userId = comment.getUser().getId();

        User user = userService.getById(userId);
        user.setRating(rating(user.getId()));
        userDao.updateUser(user);
    }

    @Override
    public List<Comment> getNotApprovedComments() {
        return commentDao.getAllComments()
                .stream()
                .filter(comment -> !comment.isApproved())
                .collect(Collectors.toList());
    }
}
