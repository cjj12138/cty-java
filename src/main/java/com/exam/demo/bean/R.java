package com.exam.demo.bean;


import com.exam.demo.constant.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS_STR = "success";

    @Getter
    @Setter
    private int code = CommonConstants.SUCCESS;

    @Getter
    @Setter
    private String msg = SUCCESS_STR;


    @Getter
    @Setter
    private transient T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = CommonConstants.FAIL;
    }
    public static R error(String msg){
        return R.builder().msg(msg).code(CommonConstants.FAIL).build();
    }
    public static R expire(String msg){
        return R.builder().msg(msg).code(CommonConstants.TOKEN_EXPIRE_OR_ERROR).build();
    }

    public static R success(String msg,Object data){
        return R.builder().code(CommonConstants.SUCCESS).msg(msg).data(data).build();
    }
    public static R success(String msg){
        return R.builder().code(CommonConstants.SUCCESS).msg(msg).build();
    }

    public R(T data, String msg, int code) {
        super();
        this.data = data;
        this.msg = msg;
        this.code = code;
    }
}

