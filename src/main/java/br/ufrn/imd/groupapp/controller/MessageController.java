package br.ufrn.imd.groupapp.controller;

import br.ufrn.imd.groupapp.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
/*
public class MessageController {

    @Autowired
    JpaRepository repository;


    @GetMapping("/message")
    List<Message> findAll() {
        return repository.findAll();
    }
    @PostMapping("/message")
    @ResponseStatus(HttpStatus.CREATED)
    Message newMessage(@RequestBody Message message) {
        return repository.save(message);
    }
}
 */
