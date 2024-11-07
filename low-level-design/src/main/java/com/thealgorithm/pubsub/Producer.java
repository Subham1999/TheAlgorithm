package com.thealgorithm.pubsub;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Producer extends Thread {
  private String producerId;
  private Broker broker;
  private List<String> topicNames;

  void produceMessage(String topicName, String msg) {
    broker.publish(topicName, new TopicMessage(producerId, msg));
  }

  @Override
  public void run() {
    while (true) {
      for (int i = 0; i < topicNames.size(); ++i) {
        produceMessage(topicNames.get(i), RandomMessageGenerator.getMessage());
        sleep0(100);
      }
      sleep0(500);
    }
  }

  private static void sleep0(long time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
