package com.company.dao;

import com.company.model.Comment;

public interface CommentDao {

    Comment getById(int id);

    void addComment(Comment comment);

    void deleteComment(Comment comment);
}
