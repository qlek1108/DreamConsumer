package com.example.demo.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private String itemName;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal currentMoney;

    private String imagePath;

    @NotNull
    private boolean groupPurchase;

    @NotNull
    private boolean moneyAutoUpdate;


    private BigDecimal autoUpdateMoneyAmount;

    private String tag;

    private String itemUri;
}
