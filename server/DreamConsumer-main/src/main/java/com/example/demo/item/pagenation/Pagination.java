package com.example.item.pagenation;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {
    private Integer page;
    private Integer size;
    private Integer currentElements;
    private Integer totalPage;
    private Long totalElements;
}