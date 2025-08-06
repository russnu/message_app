package com.russel.message.repository;

import com.russel.message.entity.SubscriberData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubscriberDataRepository extends CrudRepository<SubscriberData, Integer> {
//    Iterable<SubscriberData> findSubscribersByTopic(String topic);
    Optional<SubscriberData> findByName(String name);
}