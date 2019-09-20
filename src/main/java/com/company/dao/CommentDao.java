package com.company.dao;

import com.company.model.Comment;

import java.util.List;

public interface CommentDao {

    Comment getById(int id);

    void saveComment(Comment comment);

    void deleteComment(Comment comment);

    List<Comment> getAllComments();

    void updateComment(Comment comment);
}
