package org.shiki.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrder implements Serializable {
    private String orderNum;
    private Double totalPrice;
    private Integer status;
    private String createTime;
    private Integer userId;
}
