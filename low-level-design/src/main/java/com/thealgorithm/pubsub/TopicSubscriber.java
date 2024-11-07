package com.thealgorithm.pubsub;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopicSubscriber extends Thread {
  private Topic topic;
  private String subId;
  private String subscriberId;

  //  private AtomicInteger recievedCount = new AtomicInteger(0);

  public void pull() {
    List<TopicMessage> topicMessages = topic.fetchMessageForSubscriber(this);
    //    recievedCount.set(recievedCount.get() + topicMessages.size());
    topicMessages.forEach(
        m ->
            System.out.printf(
                "%s from %s by %s - %s\n", subscriberId, topic.getName(), subId, m.getData()));
  }

  @Override
  public void run() {
    while (true) {
      pull();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
