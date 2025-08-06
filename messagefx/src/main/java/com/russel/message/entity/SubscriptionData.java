package com.russel.message.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subscription")
@Data
public class SubscriptionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "subscriber_id", nullable = false)
    private SubscriberData subscriber;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private TopicData topic;
}
