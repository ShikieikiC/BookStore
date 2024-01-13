package org.shiki.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Integer id;
    private Integer bookId;
    private String bookName;
    private BigDecimal price;
    private Integer buyNum;
    private BigDecimal totalPrice;
    private String orderNum;
    private String createTime;
    private String imgSrc;


}
