package com.russel.message.utils;

import com.russel.message.entity.TopicData;
import com.russel.message.model.Topic;

public class TopicMapper {
    public static Topic toModel(TopicData topicData) {
        if (topicData == null) return null;
        Topic topic = new Topic();
        topic.setId(topicData.getId());
        topic.setName(topicData.getName());
        return topic;
    }

    public static TopicData toEntity(Topic topic) {
        if (topic == null) return null;
        TopicData topicData = new TopicData();
        topicData.setId(topic.getId());
        topicData.setName(topic.getName());
        return topicData;
    }
}
