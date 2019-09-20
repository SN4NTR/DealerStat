package com.company.service;

import com.company.model.Comment;

import java.util.List;

public interface CommentService {

    Comment getById(int id);

    void saveComment(Comment comment);

    void deleteComment(Comment comment);

    List<Comment> getAllComments();

    void updateComment(Comment comment);

    List<Comment> getNotApprovedCommentList();
}
