package br.ufrn.imd.groupapp.controller;

import br.ufrn.imd.groupapp.error.GroupNotFoundException;
import br.ufrn.imd.groupapp.model.Group;
import br.ufrn.imd.groupapp.model.User;
import br.ufrn.imd.groupapp.repository.GroupRepository;
import br.ufrn.imd.groupapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    ResponseEntity<List<Group>> findAll() {
        List<Group> groups = groupRepository.findAll();
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/{username}")
    ResponseEntity<User> newGroup(@PathVariable String username, @RequestBody Group newGroup) {
        Group group = groupRepository.save(newGroup);
        User newUser = new User();
        newUser.setName(username);
        newUser.setGroup(group);
        newUser = userRepository.save(newUser);
        return ResponseEntity.ok(newUser);
    }


    @GetMapping("/{id}")
    ResponseEntity<Group> findOne(@PathVariable Long id) {
        Group group = findGroup(id);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/{id}/join/{username}")
    ResponseEntity<User> joinGroup(@PathVariable String username,
                                   @PathVariable(value = "id") Long groupId) {
        Group group = findGroup(groupId);
        User newUser = new User();
        newUser.setName(username);
        newUser.setGroup(group);
        User user = userRepository.save(newUser);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/leave")
    ResponseEntity<Void> leaveGroup(@RequestParam(name = "userId") Long id) {
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
