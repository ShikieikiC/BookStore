package org.shiki.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KillOrder implements Serializable {
    private String killOrderNum;
    private Integer bookId;
    private Integer userId;
    private Integer addressId;
    private Integer status;
    private String createTime;
    private BigDecimal price;
    private Integer killNum;
}
