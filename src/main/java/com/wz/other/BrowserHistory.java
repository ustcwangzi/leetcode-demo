package com.wz.other;

import java.util.ArrayList;
import java.util.List;

/**
 * You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of steps or move forward in the history number of steps.
 * Implement the BrowserHistory class:
 * 1. BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 * 2. void visit(string url) Visits url from the current page. It clears up all the forward history.
 * 3. string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x,
 *    you will return only x steps. Return the current url after moving back in history at most steps.
 * 4. string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x,
 *    you will forward only x steps. Return the current url after forwarding in history at most steps.
 *
 * Example:
 * Input:
 * ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
 * [["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
 * Output:
 * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
 * Explanation:
 * BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
 * browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
 * browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
 * browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
 * browserHistory.back(1);                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
 * browserHistory.back(1);                   // You are in "facebook.com", move back to "google.com" return "google.com"
 * browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
 * browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
 * browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
 * browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
 * browserHistory.back(7);                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
 *
 * Constraints:
 * 1. 1 <= homepage.length <= 20
 * 2. 1 <= url.length <= 20
 * 3. 1 <= steps <= 100
 * 4. homepage and url consist of  '.' or lower case English letters.
 * 5. At most 5000 calls will be made to visit, back, and forward.
 */
public class BrowserHistory {
    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");
        browserHistory.visit("facebook.com");
        browserHistory.visit("youtube.com");
        System.out.println(browserHistory.back(1));
        System.out.println(browserHistory.back(1));
        System.out.println(browserHistory.forward(1));
        browserHistory.visit("linkedin.com");
        System.out.println(browserHistory.forward(2));
        System.out.println(browserHistory.back(2));
        System.out.println(browserHistory.back(7));
    }

    private int curIndex;
    private final List<String> history;

    /**
     * 使用 List 保存访问记录，curIndex 保存当前访问的页面索引
     */
    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
    }

    /**
     * 执行 visit 时，需要将 curIndex 之后的页面全部移除
     */
    public void visit(String url) {
        while (history.size() - 1 != curIndex) {
            history.remove(history.size() - 1);
        }
        history.add(url);
        curIndex++;
    }

    /**
     * 执行 back，curIndex 向左移动
     */
    public String back(int steps) {
        curIndex = Math.max(0, curIndex - steps);
        return history.get(curIndex);
    }

    /**
     * 执行 forward，curIndex 向右移动
     */
    public String forward(int steps) {
        curIndex = Math.min(history.size() - 1, curIndex + steps);
        return history.get(curIndex);
    }
}
