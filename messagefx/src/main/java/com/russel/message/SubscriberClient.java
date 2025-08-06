package com.russel.message;

import com.russel.message.model.Message;
import java.util.List;

public interface SubscriberClient {
    void receive(List<Message> messages);
    String getName();
}
