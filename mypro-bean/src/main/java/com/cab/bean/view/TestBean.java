package com.cab.bean.view;

import java.io.Serializable;

/**
 * Created by admin on 2018/5/18.
 */
public class TestBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
