package com.kadirugurlu.advertproject.Service;

import com.kadirugurlu.advertproject.Entity.AdvertField;
import com.kadirugurlu.advertproject.Repository.AdvertFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertFieldService implements BaseService<AdvertField> {

    private final AdvertFieldRepository advertFieldRepository;

    @Override
    public AdvertField create(AdvertField entity) {
        return advertFieldRepository.save(entity);
    }

    @Override
    public Optional<AdvertField> read(Long id) {
        return advertFieldRepository.findById(id);
    }

    @Override
    public AdvertField update(AdvertField entity, Long id) {
        AdvertField advertField = read(id).orElse(null);

        if(advertField != null) {
            advertField.setName(entity.getName());
            advertField.setValue(entity.getValue());
            return advertFieldRepository.save(advertField);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        read(id).ifPresent(advertFieldRepository::delete);
    }

    @Override
    public List<AdvertField> getAll() {
        return null;
    }

    public List<AdvertField> getByAdvertId(Long advertId) {
        return advertFieldRepository.findByAdvertId(advertId);
    }
}
