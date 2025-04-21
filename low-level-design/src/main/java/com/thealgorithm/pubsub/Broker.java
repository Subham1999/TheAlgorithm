package com.thealgorithm.pubsub;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class Broker {
  private List<Topic> topics = new ArrayList<>();

  void publish(String topic, TopicMessage topicMessage) {
    topics.stream()
        .filter(tp -> tp.getName().equals(topic))
        .forEach(tp -> tp.produce(topicMessage));
  }

  public void createTopic(String name) {
    topics.addLast(new Topic(name, new ArrayList<>(), new ArrayList<>(), new HashMap<>()));
  }

  public void addSubscriber(String topicName, TopicSubscriber topicSubscriber) {
    synchronized (topics) {
      topics.stream()
          .filter(t -> t.getName().equalsIgnoreCase(topicName))
          .forEach(t -> t.addSubscriber(topicSubscriber));
    }
  }
}
