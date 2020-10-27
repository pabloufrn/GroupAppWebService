package br.ufrn.imd.groupapp.controller;

import br.ufrn.imd.groupapp.error.GroupNotFoundException;
import br.ufrn.imd.groupapp.model.Group;
import br.ufrn.imd.groupapp.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupRepository repository;

    @GetMapping("/group")
    ResponseEntity<List<Group>> findAll() {
        List<Group> groups = repository.findAll();
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/group")
    ResponseEntity<Group> newGroup(@RequestBody Group newGroup) {
        Group group = repository.save(newGroup);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/group/{id}")
    ResponseEntity<Group> findOne(@PathVariable Long id) {
        Group group = repository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
        return ResponseEntity.ok(group);
    }

}
