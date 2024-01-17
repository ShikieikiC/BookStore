package org.shiki.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KillBook implements Serializable {
    private Integer id;
    private Integer bookId;
    private String startTime;
    private String endTime;
    private Integer killCount;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private Integer status;
    private String imgSrc;
    private String bookName;
    private String provider;
}
