package com.wz.other;

import java.util.HashMap;
import java.util.Map;

/**
 * There is an authentication system that works with authentication tokens.
 * For each session, the user will receive a new authentication token that will expire timeToLive seconds after the currentTime.
 * If the token is renewed, the expiry time will be extended to expire timeToLive seconds after the (potentially different) currentTime.
 * Implement the AuthenticationManager class:
 * 1. AuthenticationManager(int timeToLive) constructs the AuthenticationManager and sets the timeToLive.
 * 2. generate(string tokenId, int currentTime) generates a new token with the given tokenId at the given currentTime in seconds.
 * 3. renew(string tokenId, int currentTime) renews the unexpired token with the given tokenId at the given currentTime in seconds.
 *    If there are no unexpired tokens with the given tokenId, the request is ignored, and nothing happens.
 * 4. countUnexpiredTokens(int currentTime) returns the number of unexpired tokens at the given currentTime.
 * 5. Note that if a token expires at time t, and another action happens on time t (renew or countUnexpiredTokens), the expiration takes place before the other actions.
 *
 * Example 1:
 * @link ../../../../resource/AuthenticationManager.jpg
 * Input
 * ["AuthenticationManager", "renew", "generate", "countUnexpiredTokens", "generate", "renew", "renew", "countUnexpiredTokens"]
 * [[5], ["aaa", 1], ["aaa", 2], [6], ["bbb", 7], ["aaa", 8], ["bbb", 10], [15]]
 * Output
 * [null, null, null, 1, null, null, null, 0]
 * Explanation
 * AuthenticationManager authenticationManager = new AuthenticationManager(5); // Constructs the AuthenticationManager with timeToLive = 5 seconds.
 * authenticationManager.renew("aaa", 1); // No token exists with tokenId "aaa" at time 1, so nothing happens.
 * authenticationManager.generate("aaa", 2); // Generates a new token with tokenId "aaa" at time 2.
 * authenticationManager.countUnexpiredTokens(6); // The token with tokenId "aaa" is the only unexpired one at time 6, so return 1.
 * authenticationManager.generate("bbb", 7); // Generates a new token with tokenId "bbb" at time 7.
 * authenticationManager.renew("aaa", 8); // The token with tokenId "aaa" expired at time 7, and 8 >= 7, so at time 8 the renew request is ignored, and nothing happens.
 * authenticationManager.renew("bbb", 10); // The token with tokenId "bbb" is unexpired at time 10, so the renew request is fulfilled and now the token will expire at time 15.
 * authenticationManager.countUnexpiredTokens(15); // The token with tokenId "bbb" expires at time 15, and the token with tokenId "aaa" expired at time 7, so currently no token is unexpired, so return 0.
 *
 * Constraints:
 * 1. 1 <= timeToLive <= 10^8
 * 2. 1 <= currentTime <= 10^8
 * 3. 1 <= tokenId.length <= 5
 * 4. tokenId consists only of lowercase letters.
 * 5. All calls to generate will contain unique values of tokenId.
 * 6. The values of currentTime across all the function calls will be strictly increasing.
 * 7. At most 2000 calls will be made to all functions combined.
 */
public class AuthenticationManager {
    public static void main(String[] args) {
        AuthenticationManager authenticationManager = new AuthenticationManager(5);
        authenticationManager.renew("aaa", 1);
        authenticationManager.generate("aaa", 2);
        System.out.println(authenticationManager.countUnexpiredTokens(6));
        authenticationManager.generate("bbb", 7);
        authenticationManager.renew("aaa", 8);
        authenticationManager.renew("bbb", 10);
        System.out.println(authenticationManager.countUnexpiredTokens(15));
    }

    private final int timeToLive;
    private final Map<String, Integer> map;

    /**
     * 使用 map 记录 tokenId 的生存时间，renew 时将生存时间进行更新，count 时移除过期的 tokenId
     */
    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
        this.map = new HashMap<>();
    }

    public void generate(String tokenId, int currentTime) {
        map.put(tokenId, currentTime + timeToLive);
    }

    public void renew(String tokenId, int currentTime) {
        if (map.containsKey(tokenId) && map.get(tokenId) > currentTime) {
            generate(tokenId, currentTime);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        map.entrySet().removeIf(e -> e.getValue() <= currentTime);
        return map.size();
    }
}
