package org.shiki.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    private Integer id;
    private String name;
    private Integer typeId;
    private String imgSrc;
    private Double price;
}
