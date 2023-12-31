package com.kadirugurlu.advertproject.Controller;

import com.kadirugurlu.advertproject.DTO.GroupDTO;
import com.kadirugurlu.advertproject.Entity.Group;
import com.kadirugurlu.advertproject.Entity.User;
import com.kadirugurlu.advertproject.Service.GroupService;
import com.kadirugurlu.advertproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Group> create(@RequestBody GroupDTO entity){
        if(userService.read(entity.getUserId()).isPresent()) {
            Group group = fillGroup(entity);
            return ResponseEntity.ok(groupService.create(group));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public List<Group> getAll(){
        return groupService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getById(@PathVariable Long id){
        if (groupService.read(id).isPresent()) {
            return ResponseEntity.ok(groupService.read(id).orElse(null));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> update(@RequestBody GroupDTO entity, @PathVariable Long id){
        if(groupService.read(id).isPresent()) {
            Group group = fillGroup(entity);
            return ResponseEntity.ok(groupService.update(group, id));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        if(groupService.read(id).isPresent()) {
            groupService.delete(id);
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.ok(false);
        }
    }

    public Group fillGroup(GroupDTO entity){
        Group group = new Group();
        group.setName(entity.getName());
        group.setDescription(entity.getDescription());
        User user = userService.read(entity.getUserId()).orElse(null);
        return group;
    }
}
