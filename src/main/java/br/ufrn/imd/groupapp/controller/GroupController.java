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
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/group")
    ResponseEntity<List<Group>> findAll() {
        List<Group> groups = groupRepository.findAll();
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/group")
    ResponseEntity<Group> newGroup(@RequestBody Group newGroup) {
        Group group = groupRepository.save(newGroup);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/group/{id}")
    ResponseEntity<Group> findOne(@PathVariable Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
        return ResponseEntity.ok(group);
    }

    @GetMapping("group/join/{username}")
    ResponseEntity<Void> joinGroup(@PathVariable String username,
                                   @RequestParam(value = "group") Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
        userRepository.save(new User(username, group));
        return ResponseEntity.ok().build();
    }

}
