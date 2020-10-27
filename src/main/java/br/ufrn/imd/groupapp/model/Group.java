package br.ufrn.imd.groupapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "group_tb")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
}
