package br.ufrn.imd.groupapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "group_tb")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private List<User> userList;

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private List<Message> messages;
}
