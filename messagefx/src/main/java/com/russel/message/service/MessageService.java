package com.russel.message.service;

import com.russel.message.model.Message;

import java.util.List;

public interface MessageService {
    void publish(Message message);
    List<Message> getMessagesBySubscriberName(String subscriberName, String topicName);
}
