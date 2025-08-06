package com.russel.message.serviceimpl;

import com.russel.message.entity.SubscriberData;
import com.russel.message.entity.TopicData;
import com.russel.message.model.Subscriber;
import com.russel.message.model.Topic;
import com.russel.message.repository.SubscriptionDataRepository;
import com.russel.message.repository.TopicDataRepository;
import com.russel.message.service.TopicService;
import com.russel.message.utils.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicDataRepository topicDataRepository;

    @Override
    public List<Topic> fetchAllTopics() {
        List<TopicData> topicDataList = new ArrayList<>();
        topicDataRepository.findAll().forEach(topicDataList::add);

        List<Topic> allTopics = new ArrayList<>();
        for (TopicData data : topicDataList){
            Topic topic = TopicMapper.toModel(data);
            allTopics.add(topic);
        }

        return allTopics;
    }
    //================================================================================//
}
