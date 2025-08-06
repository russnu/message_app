package com.russel.message.model;

import lombok.Data;

@Data
public class Subscription {
    private Integer id;
    private Subscriber subscriber;
    private Topic topic;
}
