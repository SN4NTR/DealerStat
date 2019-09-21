package com.company.service;

import com.company.dao.PostDao;
import com.company.model.GameObject;
import com.company.model.Post;
import com.company.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    private PostDao postDao;
    private UserService userService;

    @Autowired
    public PostServiceImpl(PostDao postDao, UserService userService) {
        this.postDao = postDao;
        this.userService = userService;
    }

    @Override
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    @Override
    public void savePost(Post post) {
        User user = userService.getById(userService.findCurrentUserIdByEmail());
        post.setUser(user);
        post.setCreatedAt(new Date(new java.util.Date().getTime()));

        Set<Post> posts = new HashSet<>();
        posts.add(post);
        user.setPosts(posts);

        postDao.savePost(post);
    }

    @Override
    public void deletePost(Post post) {
        postDao.deletePost(post);
    }

    @Override
    public void updatePost(Post post) {
        post.setUser(postDao.getById(post.getId()).getUser());
        post.setGameObjects(postDao.getById(post.getId()).getGameObjects());
        postDao.updatePost(post);
    }

    @Override
    public Post getById(int id) {
        return postDao.getById(id);
    }

    @Override
    public int findPostIdByGameObjectId(int gameObjectId) {
        int postId = 0;
        List<Post> posts = postDao.getAllPosts();

        for (Post p : posts) {
            Set<GameObject> gameObjects = p.getGameObjects();

            for (GameObject go : gameObjects) {
                if (go.getId() == gameObjectId) {
                    postId = p.getId();
                    break;
                }
            }
        }

        return postId;
    }
}
