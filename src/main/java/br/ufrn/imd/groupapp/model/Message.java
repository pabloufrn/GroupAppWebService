package br.ufrn.imd.groupapp.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "message_tb")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private User user;
    @ManyToOne
    private Group group;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}