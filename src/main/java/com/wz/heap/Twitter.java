package com.wz.heap;

import java.util.*;

/**
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user
 * and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
 * 1. postTweet(userId, tweetId): Compose a new tweet.
 * 2. getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed.
 *    Each item in the news feed must be posted by users who the user followed or by the user herself.
 *    Tweets must be ordered from most recent to least recent.
 * 3. follow(followerId, followeeId): Follower follows a followee.
 * 4. unfollow(followerId, followeeId): Follower unfollows a followee.
 *
 * Example:
 * Twitter twitter = new Twitter();
 *
 * // User 1 posts a new tweet (id = 5).
 * twitter.postTweet(1, 5);
 *
 * // User 1's news feed should return a list with 1 tweet id -> [5].
 * twitter.getNewsFeed(1);
 *
 * // User 1 follows user 2.
 * twitter.follow(1, 2);
 *
 * // User 2 posts a new tweet (id = 6).
 * twitter.postTweet(2, 6);
 *
 * // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 * // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.getNewsFeed(1);
 *
 * // User 1 unfollows user 2.
 * twitter.unfollow(1, 2);
 *
 * // User 1's news feed should return a list with 1 tweet id -> [5],
 * // since user 1 is no longer following user 2.
 * twitter.getNewsFeed(1);
 */
public class Twitter {
    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1));
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
    }

    Map<Integer, List<Tweet>> userTweets;
    Map<Integer, Set<Integer>> followers;
    int timestamp = 0;

    public Twitter() {
        this.userTweets = new HashMap<>();
        this.followers = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(tweetId, ++timestamp);
        userTweets.putIfAbsent(userId, new ArrayList<>());
        userTweets.get(userId).add(tweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.timestamp));
        followers.putIfAbsent(userId, new HashSet<>());
        Set<Integer> followerSet = followers.get(userId);
        followerSet.add(userId);
        for (int id : followerSet) {
            List<Tweet> tweets = userTweets.get(id);
            if (tweets == null)
                continue;
            for (Tweet tweet : tweets) {
                queue.offer(tweet);
                if (queue.size() > 10) {
                    queue.poll();
                }
            }
        }
        LinkedList<Integer> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            result.addFirst(queue.poll().tweetId);
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        followers.putIfAbsent(followerId, new HashSet<>());
        followers.get(followerId).add(followeeId);

    }

    public void unfollow(int followerId, int followeeId) {
        followers.putIfAbsent(followerId, new HashSet<>());
        followers.get(followerId).remove(followeeId);
    }

    private static class Tweet {
        int tweetId;
        int timestamp;

        public Tweet(int tweetId, int timestamp) {
            this.tweetId = tweetId;
            this.timestamp = timestamp;
        }
    }
}
