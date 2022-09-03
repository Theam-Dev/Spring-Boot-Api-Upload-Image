package com.example.springuploadimageapi.serviceImp;

import com.example.springuploadimageapi.model.ImageEntity;
import com.example.springuploadimageapi.repository.ImageRepository;
import com.example.springuploadimageapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImp implements ImageService {
    @Autowired
    private ImageRepository repo;
    @Override
    public Iterable<ImageEntity> getAll() {
        return repo.findAll();
    }
    @Override
    public ImageEntity saveData(ImageEntity post) {
        return this.repo.save(post);
    }
    @Override
    public ImageEntity getById(Long id) {
        Optional<ImageEntity> optional = repo.findById(id);
        ImageEntity post = null;
        if (optional.isPresent()) {
            post = optional.get();
        } else {
            throw new RuntimeException(" found for id :: " + id);
        }
        return post;
    }
    @Override
    public void deleteById(Long id) {
        this.repo.deleteById(id);
    }
}
