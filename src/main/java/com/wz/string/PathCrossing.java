package com.wz.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively.
 * You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
 * Return True if the path crosses itself at any point, that is, if at any time you are on a location you've previously visited.
 * Return False otherwise.
 *
 * Example 1:
 * Input: path = "NES"
 * Output: false
 * Explanation: Notice that the path doesn't cross any point more than once.
 *
 * Example 2:
 * Input: path = "NESWW"
 * Output: true
 * Explanation: Notice that the path visits the origin twice.
 *
 * Constraints:
 * 1. 1 <= path.length <= 10^4
 * 2. path will only consist of characters in {'N', 'S', 'E', 'W}
 */
public class PathCrossing {
    public static void main(String[] args) {
        System.out.println(isPathCrossing("NES"));
        System.out.println(isPathCrossing("NESWW"));
    }

    /**
     * 判断是否再次回到 (x, y)
     */
    public static boolean isPathCrossing(String path) {
        Set<String> positions = new HashSet<>();
        positions.add(0 + "" + 0);

        int x = 0, y = 0;
        for (int i = 0; i < path.length(); i++) {
            char direction = path.charAt(i);
            switch (direction) {
                case 'N': {
                    x++;
                    break;
                }
                case 'S': {
                    x--;
                    break;
                }
                case 'E': {
                    y++;
                    break;
                }
                case 'W': {
                    y--;
                    break;
                }
            }
            if (positions.contains(x + "" + y)) {
                return true;
            }
            positions.add(x + "" + y);
        }
        return false;
    }
}
