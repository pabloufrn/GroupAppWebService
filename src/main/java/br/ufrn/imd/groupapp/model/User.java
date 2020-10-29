package br.ufrn.imd.groupapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_tb")
public class User {
    private String name;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Group group;
}
