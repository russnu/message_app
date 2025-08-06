package com.russel.message.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "subscriber")
public class SubscriberData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "subscriber", cascade = CascadeType.ALL)
    private List<SubscriptionData> subscriptions;

    @OneToMany(mappedBy = "subscriber", cascade = CascadeType.ALL)
    private List<MessageData> messages;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm a", timezone = "GMT+08:00")
    private LocalDateTime updatedAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm a", timezone = "GMT+08:00")
    private LocalDateTime createdAt;
}
