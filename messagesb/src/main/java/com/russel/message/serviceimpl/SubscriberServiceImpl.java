package com.russel.message.serviceimpl;

import com.russel.message.entity.TopicData;
import com.russel.message.model.Subscriber;
import com.russel.message.entity.SubscriberData;
import com.russel.message.entity.SubscriptionData;
import com.russel.message.model.Topic;
import com.russel.message.repository.SubscriberDataRepository;
import com.russel.message.repository.TopicDataRepository;
import com.russel.message.request.SubscriptionRequest;
import com.russel.message.utils.SubscriberMapper;
import com.russel.message.utils.TopicMapper;
import com.russel.message.service.SubscriberService;
import com.russel.message.repository.SubscriptionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    SubscriptionDataRepository subscriptionDataRepository;

    @Autowired
    SubscriberDataRepository subscriberDataRepository;

    @Autowired
    TopicDataRepository topicDataRepository;

    @Override
    public void subscribe(SubscriptionRequest subscriptionRequest) {

        String subscriberName = subscriptionRequest.getSubscriber().getName();
        String topicName = subscriptionRequest.getTopic().getName();

        SubscriberData subscriberData = subscriberDataRepository.findByName(subscriberName)
                .orElseGet(() -> {
                    Subscriber subscriber = new Subscriber(subscriberName);
                    return subscriberDataRepository.save(SubscriberMapper.toEntity(subscriber));
                });

        TopicData topicData = topicDataRepository.findByName(topicName)
                .orElseGet(() -> {
                    Topic topic = new Topic(topicName);
                    return topicDataRepository.save(TopicMapper.toEntity(topic));
                });

        boolean subscriptionExists = !subscriptionDataRepository
                .findBySubscriberAndTopic(subscriberData, topicData)
                .isEmpty();

        if (!subscriptionExists) {
            SubscriptionData subscriptionData = new SubscriptionData();
            subscriptionData.setSubscriber(subscriberData);
            subscriptionData.setTopic(topicData);
            subscriptionDataRepository.save(subscriptionData);
        }
    }
    //================================================================================//
    @Override
    public List<Subscriber> fetchAllSubscribers() {
        List<SubscriberData> subscriberDataList = new ArrayList<>();
        subscriberDataRepository.findAll().forEach(subscriberDataList::add);

        List<Subscriber> allSubscribers = new ArrayList<>();
        for (SubscriberData data : subscriberDataList){
            Subscriber subscriber = SubscriberMapper.toModel(data);
            allSubscribers.add(subscriber);
        }

        return allSubscribers;
    }
}
