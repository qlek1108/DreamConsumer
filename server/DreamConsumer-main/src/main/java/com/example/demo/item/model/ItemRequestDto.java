package com.example.item.model;


import com.example.item.db.Item;
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
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal currentMoney;

    @NotNull
    @Pattern(regexp = "^(DAILY|WEEKLY|MONTHLY)$", message = "Invalid cycle type")
    private Item.Cycle cycle;

    @NotNull
    private BigDecimal unitAmount;

    @NotNull
    private boolean autoUpdate;

    @NotNull
    private boolean groupPurchase;
}
