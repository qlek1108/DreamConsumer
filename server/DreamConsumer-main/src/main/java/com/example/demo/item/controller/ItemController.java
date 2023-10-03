package com.example.demo.item.controller;

import com.example.demo.item.db.Item;
import com.example.demo.item.mapper.ItemMapper;
import com.example.demo.item.model.ItemRequestDto;
import com.example.demo.item.model.ItemResponseDto;
import com.example.demo.item.pagenation.API;
import com.example.demo.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
@Slf4j

public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

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
}
