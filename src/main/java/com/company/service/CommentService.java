package com.company.service;

import com.company.model.Comment;

public interface CommentService {

    Comment getById(int id);

    void addComment(Comment comment);

    void deleteComment(Comment comment);
}
