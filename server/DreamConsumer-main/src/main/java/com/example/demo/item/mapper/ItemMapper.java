package com.example.demo.item.mapper;

import com.example.demo.item.db.Item;
import com.example.demo.item.model.ItemPatchDto;
import com.example.demo.item.model.ItemRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item itemRequestDtoToItem(ItemRequestDto itemRequestDto);
    void itemPatchDtoToitem(ItemPatchDto itemPatchDto, @MappingTarget Item item);
}
