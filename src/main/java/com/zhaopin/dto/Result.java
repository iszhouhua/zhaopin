package com.zhaopin.dto;

import com.zhaopin.config.Const;
import lombok.Data;

/**
 * @author ZhouHua
 * @date 2018/7/28
 */
@Data
public class Result<T> {

    private String msg = "success";

    private int code = Const.SUCCESS;

    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }
}
