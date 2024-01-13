package org.shiki.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResData {
    private Integer code;
    private String msg;
    private Object data;

    public static ResData success() {
        return new ResData(ResEnum.SUCCESS.getCode(), ResEnum.SUCCESS.getMsg(), null);
    }

    public static ResData success(Object data) {
        return new ResData(ResEnum.SUCCESS.getCode(), ResEnum.SUCCESS.getMsg(), data);
    }

    public static ResData successLogin(String token, Object data) {
        return new ResData(ResEnum.SUCCESS.getCode(), token, data);
    }


    public static ResData failed(Integer code, Object data) {
        return new ResData(code, ResEnum.FAILED.getMsg(), data);
    }
}
