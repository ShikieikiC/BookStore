package org.shiki.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData<T> {
    private Integer pageNum;
    private Integer pageSize;
    private T params;
}
