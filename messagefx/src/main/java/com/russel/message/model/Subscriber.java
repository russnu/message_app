package com.russel.message.model;

import lombok.Data;

import java.util.List;

@Data
public class Subscriber {
    private Integer id;
    private String name;
//    private List<Message> messages;

    public Subscriber() {}

    public Subscriber(String name) {
        this.name = name;
    }
}
