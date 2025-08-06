package com.russel.message.utils;

import com.russel.message.entity.MessageData;
import com.russel.message.model.Message;

public class MessageMapper {
    public static Message toModel(MessageData messageData) {
        if (messageData == null) return null;
        Message message = new Message();
        message.setId(messageData.getId());
        message.setPayload(messageData.getPayload());
        message.setTopic(TopicMapper.toModel(messageData.getTopic()));
        message.setSubscriber(SubscriberMapper.toModel(messageData.getSubscriber()));
        return message;
    }

    public static MessageData toEntity(Message message) {
        if (message == null) return null;
        MessageData messageData = new MessageData();
        messageData.setId(message.getId());
        messageData.setPayload(message.getPayload());
        messageData.setTopic(TopicMapper.toEntity(message.getTopic()));
        messageData.setSubscriber(SubscriberMapper.toEntity(message.getSubscriber()));
        return messageData;
    }
}
