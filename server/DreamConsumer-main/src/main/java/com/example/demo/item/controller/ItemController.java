package com.example.item.controller;


import com.example.item.db.Item;
import com.example.item.mapper.ItemMapper;
import com.example.item.model.ItemRequestDto;
import com.example.item.model.ItemResponseDto;
import com.example.item.pagenation.API;
import com.example.item.service.ItemImageService;
import com.example.item.service.ItemService;
import com.example.prac.db.PracticeModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@Slf4j

public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    private final ItemImageService itemImageService;

    @PostMapping("")
    public ResponseEntity saveItem(@RequestBody ItemRequestDto itemRequestDto){
       ItemResponseDto itemResponseDto = Item.EntityToItemResponse(itemService.savaItem(itemMapper.itemRequestDtoToItem(itemRequestDto)));
       return new ResponseEntity(itemResponseDto, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity getAll(@PageableDefault(size=20, sort="id",direction = Sort.Direction.DESC) Pageable pageable){
        API<List<ItemResponseDto>> listAPI = itemService.getAllItems(pageable);
        return new ResponseEntity(listAPI, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable(name = "id")Long id){
        itemService.deleteOneItem(id);
    }

    @PostMapping("/image")
    public void registerPost(
            @RequestPart(value="itemRequest", required = true) ItemRequestDto itemRequestDto,
            @RequestPart(value="image", required = true) MultipartFile image
    ) {
        itemImageService.registerPost(itemMapper.itemRequestDtoToItem(itemRequestDto),image);
        return;
    }
}
