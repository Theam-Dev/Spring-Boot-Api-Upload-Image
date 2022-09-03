package com.example.springuploadimageapi.service;

import com.example.springuploadimageapi.model.ImageEntity;

public interface ImageService {
    Iterable<ImageEntity> getAll();
    ImageEntity saveData(ImageEntity post);
    ImageEntity getById(Long id);
    void deleteById(Long id);
}
