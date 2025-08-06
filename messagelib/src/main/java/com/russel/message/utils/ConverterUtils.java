package com.russel.message.utils;

import com.russel.message.model.Subscriber;
import com.russel.message.model.Topic;
import javafx.util.StringConverter;

import java.util.List;

public class ConverterUtils {

    public static StringConverter<Subscriber> subscriberNameConverter(List<Subscriber> existingSubscribers) {
        return new StringConverter<>() {
            @Override
            public String toString(Subscriber subscriber) {
                return (subscriber == null) ? "" : subscriber.getName();
            }

            @Override
            public Subscriber fromString(String name) {
                return existingSubscribers.stream()
                        .filter(s -> s.getName().equals(name))
                        .findFirst()
                        .orElse(new Subscriber(name));
            }
        };
    }

    public static StringConverter<Topic> topicNameConverter(List<Topic> existingTopics) {
        return new StringConverter<>() {
            @Override
            public String toString(Topic topic) {
                return (topic == null) ? "" : topic.getName();
            }

            @Override
            public Topic fromString(String name) {
                return existingTopics.stream()
                        .filter(t -> t.getName().equals(name))
                        .findFirst()
                        .orElse(new Topic(name));
            }
        };
    }
}