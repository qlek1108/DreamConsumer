package com.example.item.model;


import com.example.item.db.Item;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDto {
// 공동 구매, 개인 구매 여부에 따라 response 다름(추후에 형식 정해야 함) -> 구분해두면 좋을 듯
// tag 추가해야 함
    private Long id;

    private String name;

    private String imageUrl;

    private BigDecimal price;

    private BigDecimal currentMoney;

    private Item.Cycle cycle;

    private BigDecimal unitAmount;

    private Boolean bookmark;
}
