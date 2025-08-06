package com.russel.message.repository;

import com.russel.message.entity.MessageData;
import com.russel.message.entity.SubscriberData;
import com.russel.message.entity.SubscriptionData;
import com.russel.message.entity.TopicData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionDataRepository extends CrudRepository<SubscriptionData, Integer> {
    List<SubscriptionData> findByTopic(TopicData topicData);
    List<SubscriptionData> findBySubscriberAndTopic(SubscriberData subscriber, TopicData topic);
}
