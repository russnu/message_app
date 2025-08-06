package com.russel.message.repository;

import com.russel.message.entity.TopicData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TopicDataRepository extends CrudRepository<TopicData, Integer> {
    Optional<TopicData> findByName(String name);
}
