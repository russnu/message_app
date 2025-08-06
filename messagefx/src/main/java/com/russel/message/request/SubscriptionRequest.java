package com.russel.message.request;

import com.russel.message.model.Subscriber;
import com.russel.message.model.Topic;
import lombok.Data;

@Data
public class SubscriptionRequest {
    private Subscriber subscriber;
    private Topic topic;
}
