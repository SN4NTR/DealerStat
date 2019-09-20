package com.company.dao;

import com.company.model.Post;

import java.util.List;

public interface PostDao {

    List<Post> getAllPosts();

    void savePost(Post post);

    void deletePost(Post post);

    void updatePost(Post post);

    Post getById(int id);
}
