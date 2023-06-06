package com.example.questionbank25_32.bean;

import org.litepal.crud.LitePalSupport;

/**
 * @ClassName Feed
 * @Author 史正龙
 * @date 2021.08.07 15:18
 */
public class Feed extends LitePalSupport {
    private String title;
    private String msg;
    private String time;
    private String phone;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
