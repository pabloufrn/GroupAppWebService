package br.ufrn.imd.groupapp.controller;

import br.ufrn.imd.groupapp.model.Message;
import br.ufrn.imd.groupapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository repository;


    @GetMapping("/message")
    ResponseEntity<List<Message>> findAll() {
        List<Message> msg = repository.findAll();
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/message")
    ResponseEntity<Message> newMessage(@RequestBody Message message) {
        Message msg = repository.save(message);
        return ResponseEntity.ok(msg);
    }
}
