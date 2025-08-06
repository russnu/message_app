package com.russel.message.serviceimpl;

import com.russel.message.entity.MessageData;
import com.russel.message.entity.SubscriptionData;
import com.russel.message.entity.TopicData;
import com.russel.message.exception.NotFound;
import com.russel.message.model.Message;
import com.russel.message.utils.MessageMapper;
import com.russel.message.utils.TopicMapper;
import com.russel.message.repository.MessageDataRepository;
import com.russel.message.service.MessageService;
import com.russel.message.repository.SubscriptionDataRepository;
import com.russel.message.repository.TopicDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDataRepository messageDataRepository;

    @Autowired
    TopicDataRepository topicDataRepository;

    @Autowired
    SubscriptionDataRepository subscriptionDataRepository;

    @Override
    public void publish(Message message) {

        TopicData topicData = topicDataRepository.findByName(message.getTopic().getName())
                .orElseThrow(() -> new RuntimeException("Topic not found: " + message.getTopic().getName()));

        List<SubscriptionData> subscriptionDataList = subscriptionDataRepository.
                findByTopic(topicData);

        List<MessageData> messages = new ArrayList<>();
        for (SubscriptionData subscription : subscriptionDataList) {
            MessageData messageData = new MessageData();
            messageData.setPayload(message.getPayload());
            messageData.setTopic(topicData);
            messageData.setSubscriber(subscription.getSubscriber());
            messages.add(messageData);
        }

        messageDataRepository.saveAll(messages);
    }

    @Override
    public List<Message> getMessagesBySubscriberName(String subscriberName, String topicName) {
        List<MessageData> messageDataList = messageDataRepository
                .findAllBySubscriber_NameAndTopic_Name(subscriberName, topicName);

        List<Message> messages = new ArrayList<>();
        for (MessageData data : messageDataList) {
            messages.add(MessageMapper.toModel(data));
        }

        return messages;
    }
}
