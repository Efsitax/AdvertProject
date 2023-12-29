package com.kadirugurlu.advertproject.Service;

import com.kadirugurlu.advertproject.Entity.Group;
import com.kadirugurlu.advertproject.Repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService implements BaseService<Group>{

    private final GroupRepository groupRepository;

    @Override
    public Group create(Group entity) {
        return groupRepository.save(entity);
    }

    @Override
    public Optional<Group> read(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public Group update(Group entity, Long id) {
        Group group = read(id).orElse(null);

        if(group != null) {
            group.setName(entity.getName());
            group.setDescription(entity.getDescription());
            group.setAdverts(entity.getAdverts());
            return groupRepository.save(group);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        read(id).ifPresent(groupRepository::delete);
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }
}
