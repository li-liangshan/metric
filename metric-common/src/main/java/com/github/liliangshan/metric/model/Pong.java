package com.github.liliangshan.metric.model;

/**
 * Pong .
 *
 * @author liliangshan
 * @date 2021/8/19
 */
public class Pong {

    private int code;
    private String data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getData() {
        return data;
    }

    public static Pong of(int code, String data) {
        Pong pong = new Pong();
        pong.setCode(code);
        pong.setData(data);
        return pong;
    }

    public static Pong ok(String data) {
        return of(200, data);
    }

}
