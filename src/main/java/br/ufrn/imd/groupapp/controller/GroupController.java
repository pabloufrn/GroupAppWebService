package br.ufrn.imd.groupapp.controller;

import br.ufrn.imd.groupapp.error.GroupNotFoundException;
import br.ufrn.imd.groupapp.model.Group;
import br.ufrn.imd.groupapp.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GroupController {

    @Autowired
    private GroupRepository repository;

    @GetMapping("/group")
    List<Group> findAll() {
        return repository.findAll();
    }

    @PostMapping("/group")
    @ResponseStatus(HttpStatus.CREATED)
    Group newGroup(@RequestBody Group newGroup) {
        return repository.save(newGroup);
    }

    @GetMapping("/group/{id}")
    Group findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
    }

}
