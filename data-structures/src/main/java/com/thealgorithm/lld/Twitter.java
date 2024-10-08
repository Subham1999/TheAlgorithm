package com.thealgorithm.lld;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author: Subham Santra
 */
class Tweet {
  int tweetId;
  int time;

  public Tweet(int te, int tm) {
    this.tweetId = te;
    this.time = tm;
  }

  public int hashcode() {
    return time;
  }

  public boolean equals(Tweet t) {
    if (t == null) return false;
    return t.time == time;
  }

  @Override
  public String toString() {
    return "Tweet{" + "tweetId=" + tweetId + ", time=" + time + '}';
  }
}

public class Twitter {
  private final Map<Integer, Set<Integer>> following;
  private final Map<Integer, LinkedHashSet<Tweet>> tweets;
  private final AtomicInteger atom = new AtomicInteger(0);

  public Twitter() {
    following = new HashMap<>();
    tweets = new HashMap<>();
  }

  public void postTweet(int userId, int tweetId) {
    tweets.putIfAbsent(userId, new LinkedHashSet<>());
    tweets.get(userId).add(new Tweet(tweetId, atom.incrementAndGet()));
    if (tweets.get(userId).size() > 10) {
      tweets.get(userId).removeFirst();
    }
  }

  public List<Integer> getNewsFeed(int userId) {
    //    System.out.println(following);
    //    System.out.println(tweets);
    PriorityQueue<Tweet> userFeed = new PriorityQueue<>(Comparator.comparingInt(t -> t.time));

    // add own tweets.
    addToFeed(userId, userFeed, 10);
    following.getOrDefault(userId, Set.of()).forEach(f -> addToFeed(f, userFeed, 10));

    // prepare sorted result for feed
    List<Tweet> resultant = new ArrayList<>();
    resultant.addAll(userFeed);
//    Collections.sort(resultant, (t1, t2) -> t2.time - t1.time);
    return resultant.stream().map(tweet -> tweet.tweetId).collect(Collectors.toList());
  }

  public void addToFeed(int userId, PriorityQueue<Tweet> feed, final int k) {
    LinkedHashSet<Tweet> allTweets = tweets.get(userId);

    if (allTweets == null || allTweets.isEmpty()) return;

    Iterator<Tweet> tweetIterator = allTweets.reversed().iterator();
    int cap = k;
    while (tweetIterator.hasNext() && (cap > 0)) {
      feed.add(tweetIterator.next());
      while (feed.size() > k) {
        feed.poll();
      }
      --cap;
    }
  }

  public void follow(int followerId, int followeeId) {
    following.putIfAbsent(followerId, new HashSet<>());
    following.get(followerId).add(followeeId);
  }

  public void unfollow(int followerId, int followeeId) {
    if (following.get(followerId) == null) return;
    following.get(followerId).remove(followeeId);
  }

  public static void main(String[] args) {
    Twitter twitter = new Twitter();

    twitter.postTweet(1, 1);
    twitter.postTweet(1, 2);
    twitter.postTweet(1, 3);
    twitter.postTweet(1, 4);
    twitter.postTweet(1, 5);
    twitter.postTweet(1, 6);
    twitter.postTweet(1, 7);
    twitter.postTweet(1, 8);
    twitter.postTweet(1, 9);
    twitter.postTweet(1, 10);
    twitter.postTweet(1, 11);
    System.out.println(twitter.getNewsFeed(1));
    System.out.println(twitter.getNewsFeed(4));
  }
}
