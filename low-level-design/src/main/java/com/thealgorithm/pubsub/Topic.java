package com.thealgorithm.pubsub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Builder;
import lombok.Getter;

@Builder
public class Topic {
  @Getter private String name;
  private List<TopicMessage> messages;
  private List<TopicSubscriber> subscribers;
  @Builder.Default private Map<String, Integer> subscriberVsOffset = new ConcurrentHashMap<>();

  public synchronized void produce(TopicMessage topicMessage) {
    this.messages.addLast(topicMessage);
  }

  public List<TopicMessage> fetchMessageForSubscriber(TopicSubscriber topicSubscriber) {
    subscriberVsOffset.putIfAbsent(topicSubscriber.getSubscriberId(), 0);
    Integer offset = subscriberVsOffset.get(topicSubscriber.getSubscriberId());
    List<TopicMessage> topicMessages = new ArrayList<>();
    for (int i = offset + 1; i < messages.size(); ++i) {
      topicMessages.addLast(messages.get(i));
    }
    subscriberVsOffset.put(topicSubscriber.getSubscriberId(), messages.size() - 1);
    return topicMessages;
  }

  public void addSubscriber(TopicSubscriber topicSubscriber) {
    synchronized (subscribers) {
      this.subscribers.addLast(topicSubscriber);
      topicSubscriber.setTopic(this);
    }
  }
}
