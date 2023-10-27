package com.example.demo.item.db;


import com.example.demo.item.base.BaseEntity;
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

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "current_money", nullable = false)
    private BigDecimal currentMoney;

    @Column(name = "unit_amount", nullable = false)
    private BigDecimal unitAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "cycle", nullable = false)
    private Cycle cycle;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_status", nullable = false)
    private ItemStatus itemStatus = ItemStatus.ITEM_ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_progress", nullable = false)
    private ItemProgress itemProgress = ItemProgress.ITEM_IN_PROGRESS;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "bookmark")
    private boolean bookmark = false;

    @Column(name = "group_purchase", nullable = false)
    private Boolean groupPurchase = false;

    @Column(name = "auto_update", nullable = false)
    private Boolean autoUpdate = false;

    @Column(name = "item_url")
    private String itemUrl;

    public enum Cycle {
        DAILY, WEEKLY, MONTHLY
    }

    public enum ItemStatus {
        ITEM_ACTIVE, ITEM_DELETED
    }

    public enum ItemProgress {
        ITEM_IN_PROGRESS, ITEM_COMPLETED
    }

    public static ItemResponseDto EntityToItemResponse(Item item) {
        return ItemResponseDto.builder().
                id(item.getId())
                .name(item.getName())
                .imageUrl(item.getImageUrl())
                .price(item.getPrice())
                .currentMoney(item.getCurrentMoney())
                .cycle(item.getCycle())
                .unitAmount(item.getUnitAmount())
                .bookmark(item.isBookmark())
                .build();
    }


}


