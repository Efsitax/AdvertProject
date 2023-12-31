package com.kadirugurlu.advertproject.Controller;

import com.kadirugurlu.advertproject.DTO.AdvertDTO;
import com.kadirugurlu.advertproject.DTO.AdvertFieldDTO;
import com.kadirugurlu.advertproject.DTO.AdvertGroupDTO;
import com.kadirugurlu.advertproject.DTO.CommentDTO;
import com.kadirugurlu.advertproject.Entity.*;
import com.kadirugurlu.advertproject.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advert")
public class AdvertController {

    private final AdvertService advertService;
    private final AdvertFieldService advertFieldService;
    private final AdvertCommentService advertCommentService;
    private final CategoryService categoryService;
    private final GroupService groupService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Advert> create(@RequestBody AdvertDTO entity){
        Advert advert = fillAdvert(entity);
        if(advert.getCategory() != null) {
            return ResponseEntity.ok(advertService.create(advert));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public List<Advert> getAll(){
        return advertService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Advert> getById(@PathVariable Long id){
        return ResponseEntity.ok(advertService.read(id).orElse(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Advert> update(@RequestBody AdvertDTO entity, @PathVariable Long id){
        if(advertService.read(id).isPresent()) {
            Advert advert = fillAdvert(entity);
            if(advert.getCategory() != null && !advert.getGroups().isEmpty()) {
                return ResponseEntity.ok(advertService.update(advert, id));
            }
            else {
                return ResponseEntity.badRequest().body(null);
            }
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        if(advertService.read(id).isPresent()) {
            advertService.delete(id);
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping("/field")
    public ResponseEntity<AdvertField> createField(@RequestBody AdvertFieldDTO entity) {
        AdvertField advertField = fillAdvertField(entity);
        if(advertField != null) {
            return ResponseEntity.ok(advertFieldService.create(advertField));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/field/{id}")
    public List<AdvertField> getFieldsByAdvertId(@PathVariable Long id) {
        if(advertService.read(id).isEmpty()) {
            return null;
        }
        else {
            return advertFieldService.getByAdvertId(id);
        }
    }

    @PutMapping("/field/{advert_id}/{field_id}")
    public ResponseEntity<AdvertField> updateField(@RequestBody AdvertFieldDTO entity, @PathVariable Long advert_id, @PathVariable Long field_id) {
        if(advertService.read(advert_id).isPresent() && advertFieldService.read(field_id).isPresent()) {
            AdvertField advertField = fillAdvertField(entity);
            if(advertField != null) {
                return ResponseEntity.ok(advertFieldService.update(advertField, field_id));
            }
            else {
                return ResponseEntity.badRequest().body(null);
            }
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/field/{advert_id}/{field_id}")
    public ResponseEntity<Boolean> deleteField(@PathVariable Long advert_id, @PathVariable Long field_id) {
        if(advertService.read(advert_id).isPresent() && advertFieldService.read(field_id).isPresent()) {
            advertFieldService.delete(field_id);
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping("/comment")
    public ResponseEntity<AdvertComment> createComment(@RequestBody CommentDTO entity) {
        AdvertComment advertComment = fillComment(entity);
        if(advertComment != null) {
            return ResponseEntity.ok(advertCommentService.create(advertComment));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/comment/{id}")
    public List<AdvertComment> getCommentsByAdvertId(@PathVariable Long id) {
        if(advertService.read(id).isEmpty()) {
            return null;
        }
        else {
            return advertCommentService.getByAdvertId(id);
        }
    }

    @PutMapping("/comment/{advert_id}/{comment_id}")
    public ResponseEntity<AdvertComment> updateComment(@RequestBody CommentDTO entity, @PathVariable Long advert_id, @PathVariable Long comment_id) {
        if(advertService.read(advert_id).isPresent() && advertCommentService.read(comment_id).isPresent()) {
            AdvertComment advertComment = fillComment(entity);
            if(advertComment != null) {
                return ResponseEntity.ok(advertCommentService.update(advertComment, comment_id));
            }
            else {
                return ResponseEntity.badRequest().body(null);
            }
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/comment/{advert_id}/{comment_id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long advert_id, @PathVariable Long comment_id) {
        if(advertService.read(advert_id).isPresent() && advertCommentService.read(comment_id).isPresent()) {
            advertCommentService.delete(comment_id);
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.ok(false);
        }
    }

    @PutMapping("/group")
    public ResponseEntity<Advert> addGroup(@RequestBody AdvertGroupDTO entity) {
        if(advertService.read(entity.getAdvertId()).isPresent()) {
            Advert advert = advertService.read(entity.getAdvertId()).orElse(null);
            advert.getGroups().add(groupService.getByName(entity.getName()).orElse(null));
            return ResponseEntity.ok(advertService.update(advert, entity.getAdvertId()));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/group/delete")
    public ResponseEntity<Advert> deleteGroup(@RequestBody AdvertGroupDTO entity) {
        if(advertService.read(entity.getAdvertId()).isPresent()) {
            Advert advert = advertService.read(entity.getAdvertId()).orElse(null);
            advert.getGroups().remove(groupService.getByName(entity.getName()).orElse(null));
            return ResponseEntity.ok(advertService.update(advert, entity.getAdvertId()));
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    private Advert fillAdvert(AdvertDTO entity) {
        if(categoryService.getByName(entity.getCategory()).isPresent()) {
            Advert advert = new Advert();
            advert.setTitle(entity.getTitle());
            advert.setDescription(entity.getDescription());
            advert.setCategory(categoryService.getByName(entity.getCategory()).orElse(null));
            if(entity.getGroupIds() != null) {
                for (Long groupId : entity.getGroupIds()) {
                    if(groupService.read(groupId).isPresent()) {
                        Group group = groupService.read(groupId).orElse(null);
                        if (group != null) {
                            advert.getGroups().add(group);
                        }
                    }
                }
            }
            advert.setUser(userService.read(entity.getUserId()).orElse(null));
            return advert;
        }
        else {
            return null;
        }
    }

    private AdvertField fillAdvertField(AdvertFieldDTO entity) {
        if(advertService.read(entity.getAdvertId()).isPresent()) {
            AdvertField advertField = new AdvertField();
            advertField.setName(entity.getName());
            advertField.setValue(entity.getValue());
            advertField.setAdvert(advertService.read(entity.getAdvertId()).orElse(null));
            return advertField;
        }
        else {
            return null;
        }
    }


    public AdvertComment fillComment(CommentDTO entity) {
        if(advertService.read(entity.getAdvertId()).isPresent() && userService.read(entity.getUserId()).isPresent()) {
            AdvertComment advertComment = new AdvertComment();
            advertComment.setTitle(entity.getTitle());
            advertComment.setComment(entity.getComment());
            advertComment.setAdvert(advertService.read(entity.getAdvertId()).orElse(null));
            advertComment.setUser(userService.read(entity.getUserId()).orElse(null));
            return advertComment;
        }
        else {
            return null;
        }
    }
}
