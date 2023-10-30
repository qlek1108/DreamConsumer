package com.example.item.service;

import com.example.item.db.Item;
import com.example.prac.db.PracticeModel;
import com.example.prac.image.FileSystemStorageService;
import com.example.prac.service.PracticeModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemImageService {
    private final FileSystemStorageService fileSystemStorageService;
    private final ItemService itemService;

    public void registerPost(Item item, MultipartFile image){
        String uri = fileSystemStorageService.store(image,item.getName());
        item.setImageUrl(uri);
        itemService.savaItem(item);
    }
}
