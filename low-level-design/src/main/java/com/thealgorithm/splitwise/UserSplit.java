package com.thealgorithm.splitwise;

import lombok.Builder;
import lombok.Value;

/**
 * @author: Subham Santra
 */
@Value
@Builder
public class UserSplit {
  User user;
  Double shareAmount;
}
