package com.kadirugurlu.advertproject.Service;

import com.kadirugurlu.advertproject.Entity.Advert;
import com.kadirugurlu.advertproject.Repository.AdvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertService implements BaseService<Advert>{

    private final AdvertRepository advertRepository;

    @Override
    public Advert create(Advert entity) {
        return advertRepository.save(entity);
    }

    @Override
    public Optional<Advert> read(Long id) {
        return advertRepository.findById(id);
    }

    @Override
    public Advert update(Advert entity, Long id) {
        Advert advert = read(id).orElse(null);

        if(advert != null) {
            advert.setTitle(entity.getTitle());
            advert.setDescription(entity.getDescription());
            advert.setPrice(entity.getPrice());
            return advertRepository.save(advert);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        read(id).ifPresent(advertRepository::delete);
    }

    @Override
    public List<Advert> getAll() {
        return advertRepository.findAll();
    }

    public List<Advert> getByCategoryId(Long categoryId) {
        return advertRepository.findByCategoryId(categoryId);
    }

    public List<Advert> getByUserId(Long userId) {
        return advertRepository.findByUserId(userId);
    }

    public List<Advert> getByGroupId(Long groupId) {
        return advertRepository.findByGroupsId(groupId);
    }
}
