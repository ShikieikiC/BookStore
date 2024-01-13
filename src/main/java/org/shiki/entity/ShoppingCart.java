package org.shiki.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    private Integer id;
    private Integer buyNum;
    private BigDecimal buyPrice;
    private BigDecimal totalPrice;
    private Integer userId;
    private String createTime;
    private Integer status;
    private Integer bookId;
    private String imgSrc;
    private String bookName;
    private Integer storeCount;
    private BigDecimal price;
}
