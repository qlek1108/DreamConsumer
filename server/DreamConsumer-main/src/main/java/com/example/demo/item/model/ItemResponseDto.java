package com.example.demo.item.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDto {

    private Long id;

    private Long userId;

    private String itemName;

    private BigDecimal price;

    private BigDecimal currentMoney;

    private String imagePath;

    private Boolean groupPurchase;

    private Boolean moneyAutoUpdate;

    private BigDecimal autoUpdateMoneyAmount;

    private String tag;

    private String itemUri;
}
