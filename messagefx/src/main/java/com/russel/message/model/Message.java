package com.russel.message.model;

import lombok.Data;

@Data
public class Message {
    Integer id;
    String payload;
    Topic topic;
    Subscriber subscriber;

    public Message() {}

    public Message(Topic topic, String payload) {
        this.topic = topic;
        this.payload = payload;
    }
}
