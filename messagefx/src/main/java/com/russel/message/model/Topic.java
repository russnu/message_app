package com.russel.message.model;

import lombok.Data;

@Data
public class Topic {
    private Integer id;
    private String name;

    public Topic() {}

    public Topic(String name) {
        this.name = name;
    }
}
