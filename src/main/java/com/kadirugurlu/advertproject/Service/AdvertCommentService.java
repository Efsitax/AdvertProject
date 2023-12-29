package com.kadirugurlu.advertproject.Service;

import com.kadirugurlu.advertproject.Entity.AdvertComment;
import com.kadirugurlu.advertproject.Entity.Category;
import com.kadirugurlu.advertproject.Repository.AdvertCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertCommentService implements BaseService<AdvertComment>{

    private final AdvertCommentRepository advertCommentRepository;

    @Override
    public AdvertComment create(AdvertComment entity) {
        return advertCommentRepository.save(entity);
    }

    @Override
    public Optional<AdvertComment> read(Long id) {
        return advertCommentRepository.findById(id);
    }

    @Override
    public AdvertComment update(AdvertComment entity, Long id) {
        AdvertComment advertComment = read(id).orElse(null);

        if(advertComment != null) {
            advertComment.setComment(entity.getComment());
            advertComment.setTitle(entity.getTitle());
            return advertCommentRepository.save(advertComment);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        read(id).ifPresent(advertCommentRepository::delete);
    }

    @Override
    public List<AdvertComment> getAll() {
        return advertCommentRepository.findAll();
    }
}
