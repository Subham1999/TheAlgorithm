package com.thealgorithm.pubsub;

import java.util.List;

public class Test {
  public static void main(String[] args) {
    Broker broker = new Broker();
    broker.createTopic("topic_1");

    TopicSubscriber sub2 = TopicSubscriber.builder().subId("1247").subscriberId("sub2").build();
    TopicSubscriber sub1 = TopicSubscriber.builder().subId("1245").subscriberId("sub1").build();
    TopicSubscriber sub11 = TopicSubscriber.builder().subId("1246").subscriberId("sub1").build();
    broker.addSubscriber(
        "topic_1", sub1);
    broker.addSubscriber(
        "topic_1", sub11);
    broker.addSubscriber(
        "topic_1", sub2);

    sub1.start();
    sub11.start();
    sub2.start();

    Producer producer1 = new Producer("prod_1", broker, List.of("topic_1"));
    Producer producer2 = new Producer("prod_1", broker, List.of("topic_1"));
    Producer producer3 = new Producer("prod_1", broker, List.of("topic_1"));

    producer1.start();
    producer2.start();
    producer3.start();
  }
}
