package br.ufrn.imd.groupapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "message_tb")
public class Message {

    public Message(String text, Group group){
        this.text = text;
        this.group = group;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String author;
    @ManyToOne
    private Group group;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}