package com.company.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "game")
public class Game {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
}
