package org.shiki.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer id;
    private String name;
    private Integer typeId;
    private String provider;
    private String author;
    private BigDecimal price;
    private String detail;
    private String imgSrc;
    private Integer collectionCount;
    private Integer storeCount;
    private Integer buyCount;
    private Integer readCount;
    private String createTime;
    private Integer status;


}
