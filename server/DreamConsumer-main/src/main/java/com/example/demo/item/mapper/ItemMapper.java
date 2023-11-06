package com.example.item.mapper;


import com.example.item.db.Item;
import com.example.item.model.ItemPatchDto;
import com.example.item.model.ItemRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item itemRequestDtoToItem(ItemRequestDto itemRequestDto);
    void itemPatchDtoToitem(ItemPatchDto itemPatchDto, @MappingTarget Item item);
}
