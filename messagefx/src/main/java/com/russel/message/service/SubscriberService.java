package com.russel.message.service;

import com.russel.message.model.Subscriber;
import com.russel.message.request.SubscriptionRequest;

import java.util.List;

public interface SubscriberService {
    void subscribe(SubscriptionRequest subscriptionRequest);
    List<Subscriber> fetchAllSubscribers();
}
