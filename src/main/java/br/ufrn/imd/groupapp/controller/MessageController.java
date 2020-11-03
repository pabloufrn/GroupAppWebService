package br.ufrn.imd.groupapp.controller;

import br.ufrn.imd.groupapp.model.Message;
import br.ufrn.imd.groupapp.model.User;
import br.ufrn.imd.groupapp.repository.MessageRepository;
import br.ufrn.imd.groupapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/message")
    ResponseEntity<List<Message>> findAll(
            @RequestParam(value = "group") Long groupId,
            @RequestParam(value = "from") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") Date from
    ) {
        List<Message> msg = repository.findAllByGroupIdAndDateAfter(groupId, from);
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/message")
    ResponseEntity<Message> newMessage(@RequestParam(value = "userId") Long userId,
                                       @RequestBody Message message) {
        User user = userRepository.getOne(userId);
        message.setAuthor(user.getName());
        message.setGroup(user.getGroup());
        Message msg = repository.save(message);
        return ResponseEntity.ok(msg);
    }
}
