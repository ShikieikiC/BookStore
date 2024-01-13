package org.shiki.exception;

import org.shiki.utils.ResData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PasswordException.class)
    public ResData handlePasswordException() {
        return ResData.failed(1001, "用户名或密码错误");
    }

    @ExceptionHandler(NoLoginException.class)
    public ResData handleNoLoginException() {
        return ResData.failed(1002, "未登录");
    }

    @ExceptionHandler(BookNotExistException.class)
    public ResData handleBookNotExistException() {
        return ResData.failed(1003, "图书不存在");
    }

    @ExceptionHandler(NoSelectException.class)
    public ResData handleNoSelectException() {
        return ResData.failed(1004, "未选择任何项");
    }

    @ExceptionHandler(OutOfStoreException.class)
    public ResData handleOutOfStoreException(OutOfStoreException ooe) {
        return ResData.failed(1005, String.join("、", ooe.getBookNames()) + " 库存不足");
    }

    @ExceptionHandler(SystemBusyException.class)
    public ResData handleSystemBusyException() {
        return ResData.failed(2000, "系统繁忙");
    }


}
