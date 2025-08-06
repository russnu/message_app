package com.russel.message.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "message")
public class MessageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String payload;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private TopicData topic;

    @ManyToOne
    @JoinColumn(name = "subscriber_id", nullable = false)
    private SubscriberData subscriber;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm a", timezone = "GMT+08:00")
    private LocalDateTime updatedAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm a", timezone = "GMT+08:00")
    private LocalDateTime createdAt;
}
