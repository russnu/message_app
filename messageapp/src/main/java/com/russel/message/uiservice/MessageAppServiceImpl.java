package com.russel.message.uiservice;

import com.russel.message.request.SubscriptionRequest;
import com.russel.message.model.Message;
import com.russel.message.MessageAppService;
import com.russel.message.SubscriberClient;
import com.russel.message.model.Subscriber;
import com.russel.message.model.Topic;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MessageAppServiceImpl implements MessageAppService {
    String url = "http://localhost:8080/api/message";
    SubscriberClient subscriberClient;

    public MessageAppServiceImpl() {}

    @Override
    public void subscribe(Topic topic, SubscriberClient subscriberClient) {
        Subscriber subscriber = new Subscriber();
        subscriber.setName(subscriberClient.getName());

        SubscriptionRequest requestBody = new SubscriptionRequest();
        requestBody.setSubscriber(subscriber);
        requestBody.setTopic(topic);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SubscriptionRequest> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(url + "/subscribe", entity, String.class);
    }
    //================================================================================//
    @Override
    public void publish(Message message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Message> request = new HttpEntity<>(message, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url + "/publish", request, String.class);
    }
    //================================================================================//
    @Override
    public List<Message> getMessages(String subscriberName, String topicName) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Message>> response = restTemplate.exchange(
                url + "/" + subscriberName + "?topicName=" + topicName,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Message>>() {}
        );
        return response.getBody();
    }
    //================================================================================//
    @Override
    public List<Subscriber> getAllSubscribers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Subscriber>> response = restTemplate.exchange(
                url + "/subscriber",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Subscriber>>() {}
        );
        return response.getBody();
    }
    //================================================================================//
    @Override
    public List<Topic> getAllTopics() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Topic>> response = restTemplate.exchange(
                url + "/topic",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Topic>>() {}
        );
        return response.getBody();
    }
}
