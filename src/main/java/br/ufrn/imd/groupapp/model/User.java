package br.ufrn.imd.groupapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    private String name;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Group group;
}
