package com.company.service;

import com.company.dao.GameObjectDao;
import com.company.model.Game;
import com.company.model.GameObject;
import com.company.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class GameObjectServiceImpl implements GameObjectService {

    private GameObjectDao gameObjectDao;
    private PostService postService;

    public static int postIdBuffer;

    @Autowired
    public GameObjectServiceImpl(GameObjectDao gameObjectDao, PostService postService) {
        this.gameObjectDao = gameObjectDao;
        this.postService = postService;
    }

    @Override
    public GameObject getById(int id) {
        return gameObjectDao.getById(id);
    }

    @Override
    public void addGameObject(GameObject gameObject) {
        Post post = postService.getById(postIdBuffer);

        Set<Post> posts = new HashSet<>();
        posts.add(post);
        gameObject.setPosts(posts);

        Set<GameObject> gameObjects = new HashSet<>();
        gameObjects.add(gameObject);
        post.setGameObjects(gameObjects);

        gameObject.setCreatedAt(new Date(new java.util.Date().getTime()));
        gameObject.setUpdatedAt(gameObject.getCreatedAt());
        gameObject.setStatus(true);

        gameObjectDao.addGameObject(gameObject);

        GameServiceImpl.gameObjectIdBuffer = gameObject.getId();
    }

    @Override
    public void updateGameObject(GameObject gameObject) {
        Game game = gameObjectDao.getById(gameObject.getId()).getGame();
        if (game != null) {
            gameObject.setGame(game);
        }
        gameObject.setPosts(gameObjectDao.getById(gameObject.getId()).getPosts());
        gameObject.setUpdatedAt(new Date(new java.util.Date().getTime()));
        gameObject.setStatus(true);
        gameObjectDao.updateGameObject(gameObject);
    }

    @Override
    public void deleteGameObject(GameObject gameObject) {
        gameObjectDao.deleteGameObject(gameObject);
    }
}
