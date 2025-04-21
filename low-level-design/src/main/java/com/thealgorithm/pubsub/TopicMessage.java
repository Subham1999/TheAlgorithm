package com.thealgorithm.pubsub;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopicMessage {
  private String producer;
  private String data;
}
