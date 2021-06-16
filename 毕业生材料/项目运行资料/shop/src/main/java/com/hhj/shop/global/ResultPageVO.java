package com.hhj.shop.global;

import lombok.Getter;

@Getter
public class ResultPageVO<T> {

    private T data;


    public ResultPageVO(T data) {
        this.data = data;
    }

}
