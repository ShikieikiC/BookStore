package org.shiki.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Integer id;
    private String name;
    private Integer typeId;
    private String brand;
    private BigDecimal price;
    private Integer storeCount;
    private Integer buyCount;
    private String imgSrc;
    private String createTime;
    private Integer order;
    private Integer status;
}
