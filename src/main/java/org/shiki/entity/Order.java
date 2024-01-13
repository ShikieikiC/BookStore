package org.shiki.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String orderNum;
    private BigDecimal totalPrice;
    private Integer userId;
    private Integer addressId;
    private String createTime;
    private Integer status;
    private Integer score;
}
