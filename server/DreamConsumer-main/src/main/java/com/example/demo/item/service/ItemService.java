package com.example.demo.item.service;

import com.example.demo.item.db.Item;
import com.example.demo.item.db.ItemRepository;
import com.example.demo.item.model.ItemResponseDto;
import com.example.demo.item.pagenation.API;
import com.example.demo.item.pagenation.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Item savaItem(Item item){
       return itemRepository.save(item);
    }
    @Transactional(readOnly = true)
    public Item getOneItem(Long itemId){
        return  itemRepository.findById(itemId).orElseThrow(()->new NoSuchElementException());
    }

    @Transactional(readOnly = true)
    public API<List<ItemResponseDto>> getAllItems(Pageable pageable){
        var list = itemRepository.findAll(pageable);
        var pagination = Pagination.builder().page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        List<Item> items = list.getContent();
        List<ItemResponseDto> itemResponseDtos = items.stream().map(a -> a.EntityToItemResponse(a)).collect(Collectors.toList());
        return API.<List<ItemResponseDto>>builder().body(itemResponseDtos).pagination(pagination).build();
    }

    @Transactional
    public void deleteOneItem(Long itemId){
        Item item = getOneItem(itemId);
        item.setDeleted(true);
        savaItem(item);
    }
}
