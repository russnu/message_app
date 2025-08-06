package com.russel.message.utils;

import com.russel.message.model.Subscriber;
import com.russel.message.entity.SubscriberData;

public class SubscriberMapper {
    public static Subscriber toModel(SubscriberData subscriberData) {
        if (subscriberData == null) return null;
        Subscriber subscriber = new Subscriber();
        subscriber.setId(subscriberData.getId());
        subscriber.setName(subscriberData.getName());
        return subscriber;
    }

    public static SubscriberData toEntity(Subscriber subscriber) {
        if (subscriber == null) return null;
        SubscriberData subscriberData = new SubscriberData();
        subscriberData.setId(subscriber.getId());
        subscriberData.setName(subscriber.getName());
        return subscriberData;
    }
}
