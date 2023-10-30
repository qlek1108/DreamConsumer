package com.example.item.pagenation;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class API<t> {
    private  t body ;
    private Pagination pagination;
}

