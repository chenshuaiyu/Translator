package com.example.chen.translator.data.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/5 9:37
 */
@Entity
public class Translation {
    @Id(autoincrement = true)
    private Long id;
    private String input;
    private String output;
    private Boolean isCollected;

    @Generated(hash = 1617815660)
    public Translation(Long id, String input, String output, Boolean isCollected) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.isCollected = isCollected;
    }

    @Generated(hash = 321689573)
    public Translation() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInput() {
        return this.input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return this.output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Boolean getIsCollected() {
        return this.isCollected;
    }

    public void setIsCollected(Boolean isCollected) {
        this.isCollected = isCollected;
    }
}
