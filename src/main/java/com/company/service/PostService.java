package com.company.service;

import com.company.model.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    void savePost(Post post);

    void deletePost(Post post);

    void updatePost(Post post);

    Post getById(int id);
}
