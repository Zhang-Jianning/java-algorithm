package com.zjn.queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class TwitterTimeline {

    // 时间
    private static int timestamp;

    // 所有的用户
    private Map<Integer, User> userMap = new HashMap<>();


    /** user 发表一条 tweet 动态 */
    public void postTweet(int userId, int tweetId) {

        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        User user = userMap.get(userId);
        user.post(tweetId);

    }

    /** follower 关注 followee */
    public void follow(int followerId, int followeeId) {

        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new User(followeeId));
        }
        User user = userMap.get(followerId);
        // 关注
        user.follow(followeeId);

    }


    /** follower 取关 followee，如果 Id 不存在则什么都不做 */
    public void unfollow(int followerId, int followeeId) {

        if (!userMap.containsKey(followerId)) {
            return;
        }
        userMap.get(followerId).unfollow(followeeId);

    }


    /** 返回该 user 关注的人（包括他自己）最近的动态 id，
     最多 10 条，而且这些动态必须按从新到旧的时间线顺序排列。*/
    public List<Integer> getNewsFeed(int userId) {

        User user = userMap.get(userId);
        Set<Integer> followed = user.followed;

        // 最大堆
        PriorityQueue<Tweet> queue = new PriorityQueue<>(followed.size(), (a, b) -> {
            return b.time - a.time;
        });

        for (Integer uid : followed) {
            Tweet head = userMap.get(uid).head;
            if (head == null) continue;
            queue.add(head);
        }

        List result = new ArrayList<Integer>();

        while (!queue.isEmpty()) {
            if (result.size() == 10) {
                break;
            }
            // 拿出时间最早的元素
            Tweet poll = queue.poll();
            result.add(poll);
            // 将next元素再次插入队列排序
            if (poll.next != null) {
                queue.add(poll.next);
            }
        }
        return result;
    }


    // 用户实体
    private static class User{
        // uid
        private int id;
        // 关注的用户
        private Set<Integer> followed;
        // 用户发推的链表头节点
        private Tweet head;

        public User(int id) {
            this.id = id;
            this.followed = new HashSet<>();
            this.head = null;
            follow(id);
        }

        // 关注
        public void follow(int uid) {
            this.followed.add(uid);
        }

        // 取消关注
        public void unfollow(int uid) {
            if (this.id == uid) {
                return;
            }
            followed.remove(uid);
        }

        // 发推
        public void post(int tweetId) {
            Tweet tweet = new Tweet(tweetId, timestamp);
            // 时间累加
            timestamp ++;

            // 时间大的节点指向时间小的节点，头节点是时间大的节点
            tweet.next = this.head;
            this.head = tweet;

        }

    }

    // 推文实体
    private static class Tweet{
        // Tweet id
        private int id;
        // 发推时间
        private int time;
        // 下个节点   节点是从时间大的指向时间小的
        private Tweet next;

        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
            this.next = null;
        }
    }


}
