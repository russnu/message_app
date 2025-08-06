package com.russel.message;

import com.russel.message.model.Message;
import com.russel.message.model.Subscriber;
import com.russel.message.model.Topic;

import java.util.List;

public interface MessageAppService {
    void subscribe(Topic topic, SubscriberClient subscriberClient);
    void publish(Message message);
    List<Message> getMessages(String subscriberName, String topicName);
    List<Subscriber> getAllSubscribers();
    List<Topic> getAllTopics();
}
