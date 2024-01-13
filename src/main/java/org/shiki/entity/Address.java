package org.shiki.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Integer id;
    private String province;
    private String city;
    private String area;
    private String detailAddress;
    private String telephone;
    private String receiver;
    private Integer userId;
    private Integer status;
    private Integer isDefault;

}
