package com.company.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {

    @Getter
    @Setter
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name = "title")
    private String title;

    @Getter
    @Setter
    @Column(name = "created_at")
    private Date createdAt;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "gameObject_post",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "gameObject_id")})
    private Set<GameObject> gameObjects;

//    @Getter
//    @Setter
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "game_post",
//            joinColumns = {@JoinColumn(name = "post_id")},
//            inverseJoinColumns = {@JoinColumn(name = "game_id")})
//    private Set<Game> games;
}
