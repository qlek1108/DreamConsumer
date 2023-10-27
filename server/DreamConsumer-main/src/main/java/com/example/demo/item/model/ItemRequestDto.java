package com.example.demo.item.model;

import com.example.demo.item.db.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.File;
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

    private File image;

    @NotNull
    private BigDecimal price;

    // 태그 추가해야 함

    private String itemUrl;

    @NotNull
    private BigDecimal currentMoney;

    @NotNull
    @Pattern(regexp = "^(daily|weekly|monthly)$", message = "Invalid cycle type")
    private Item.Cycle cycle;

    @NotNull
    private BigDecimal unitAmount;

    @NotNull
    private boolean autoUpdate;

    @NotNull
    private boolean groupPurchase;
}
