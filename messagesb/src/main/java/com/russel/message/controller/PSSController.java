package com.russel.message.controller;

import com.russel.message.model.Subscriber;
import com.russel.message.exception.ErrorResponse;
import com.russel.message.model.Message;
import com.russel.message.model.Topic;
import com.russel.message.request.SubscriptionRequest;
import com.russel.message.service.MessageService;
import com.russel.message.service.SubscriberService;
import com.russel.message.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/message")
@RestController
public class PSSController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private SubscriberService subscriberService;
    @Autowired
    private TopicService topicService;
    //========================================================================================================//
    @PostMapping("/publish")
    public ResponseEntity<?> publish(@RequestBody Message message){
        try {
            messageService.publish(message);
            return ResponseEntity.ok(message);
        }
        catch(Exception ex) {
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Internal Server Error",
                    ex.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }

    }
    //========================================================================================================//
    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody SubscriptionRequest subscriptionRequest){
        ResponseEntity<?> response;
        try {
            subscriberService.subscribe(subscriptionRequest);
            response = ResponseEntity.ok(subscriptionRequest);
        }
        catch( Exception ex)
        {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return response;
    }
    //========================================================================================================//
    @GetMapping("/{subscriberName}")
    public ResponseEntity<?> getMessages(@PathVariable String subscriberName, @RequestParam String topicName){
        try {
            List<Message> messages = messageService.getMessagesBySubscriberName(subscriberName, topicName);
            return ResponseEntity.ok(messages);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
    //========================================================================================================//
    @GetMapping("/subscriber")
    public ResponseEntity<?> getAllSubscribers(){
        try {
            List<Subscriber> allSubscribers = subscriberService.fetchAllSubscribers();
            return ResponseEntity.ok(allSubscribers);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
    //========================================================================================================//
    @GetMapping("/topic")
    public ResponseEntity<?> getAllTopics(){
        try {
            List<Topic> allTopics = topicService.fetchAllTopics();
            return ResponseEntity.ok(allTopics);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
