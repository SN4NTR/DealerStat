package com.company.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "game_object")
public class GameObject {

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
    @Column(name = "text")
    private String text;

    @Getter
    @Setter
    @Column(name = "status")
    private boolean status;

    @Getter
    @Setter
    @Column(name = "created_at")
    private Date createdAt;

    @Getter
    @Setter
    @Column(name = "updated_at")
    private Date updatedAt;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "gameObject_post",
            joinColumns = {@JoinColumn(name = "gameObject_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id")})
    private Set<Post> posts;
}
