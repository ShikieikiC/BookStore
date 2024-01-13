package org.shiki.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adv {
    private Integer id;
    private String imgSrc;
    private String title;
    private Integer typeId;
    private String startTime;
    private String endTime;
    private Integer status;
}
