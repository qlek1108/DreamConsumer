package com.example.demo.item.db;


import com.example.demo.item.base.BaseEntity;
import com.example.demo.item.model.ItemRequestDto;
import com.example.demo.item.model.ItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted = false")
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "current_money", nullable = false)
    private BigDecimal currentMoney;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "group_purchase", nullable = false)
    private Boolean groupPurchase = false;

    @Column(name = "money_auto_update", nullable = false)
    private Boolean moneyAutoUpdate;

    @Column(name = "auto_update_money_amount")
    private BigDecimal autoUpdateMoneyAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "tag")
    private Tags tag;

    @Column(name = "Uri")
    private String itemUri;


    public enum Tags {
        HOUSE("집"), CAR("차");

        private String element;

        Tags(String element) {
            this.element = element;
        }
    }

    public static ItemResponseDto EntityToItemResponse(Item item) {
        return ItemResponseDto.builder().
                id(item.getId())
                .userId(item.getUserId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .currentMoney(item.getCurrentMoney())
                .imagePath(item.getImagePath())
                .groupPurchase(item.getGroupPurchase())
                .moneyAutoUpdate(item.getMoneyAutoUpdate())
                .autoUpdateMoneyAmount(item.getAutoUpdateMoneyAmount())
                .tag(item.getTag().element)
                .itemUri(item.getItemUri()).build();
    }


}


