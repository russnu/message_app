package com.russel.message.repository;

import com.russel.message.entity.MessageData;
import com.russel.message.entity.TopicData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageDataRepository extends CrudRepository<MessageData,Integer> {
    List<MessageData> findByTopic(TopicData topic);
    List<MessageData> findAllBySubscriber_Name(String subscriberName);
    List<MessageData> findAllBySubscriber_NameAndTopic_Name(String subscriberName, String topicName);

    void deleteAllBySubscriberName(String subscriberName);
}
