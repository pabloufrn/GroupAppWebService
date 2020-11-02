package br.ufrn.imd.groupapp.controller;

import br.ufrn.imd.groupapp.error.GroupNotFoundException;
import br.ufrn.imd.groupapp.model.Group;
import br.ufrn.imd.groupapp.model.Message;
import br.ufrn.imd.groupapp.model.User;
import br.ufrn.imd.groupapp.repository.GroupRepository;
import br.ufrn.imd.groupapp.repository.MessageRepository;
import br.ufrn.imd.groupapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/group")
    ResponseEntity<List<Group>> findAll() {
        List<Group> groups = groupRepository.findAll();
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/group")
    ResponseEntity<User> newGroup(@RequestBody User user) {
        Group group = groupRepository.save(user.getGroup());
        user.setGroup(group);
        User newUser = userRepository.save(user);
        messageRepository.save(new Message(user.getName() + " entrou no grupo!", group));
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/group/{id}")
    ResponseEntity<Group> findOne(@PathVariable Long id) {
        Group group = findGroup(id);
        return ResponseEntity.ok(group);
    }

    @PostMapping("group/{id}/user")
    ResponseEntity<User> joinGroup(@PathVariable(value = "id") Long groupId,
                                   @RequestBody User user) {
        Group group = findGroup(groupId);
        user.setGroup(group);
        User newUser = userRepository.save(user);
        messageRepository.save(new Message(user.getName() + " entrou no grupo!", group));
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/user/{id}")
    ResponseEntity<Void> leaveGroup(@PathVariable(name = "id") Long id) {
        User user = userRepository.getOne(id);
        Long groupId = user.getGroup().getId();
        userRepository.delete(user);
        Group group = groupRepository.getOne(groupId);
        if(group.getUserList().isEmpty()) {
            groupRepository.delete(group);
        }
        return ResponseEntity.ok().build();
    }

    private Group findGroup(Long id){
        return groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
    }
}
