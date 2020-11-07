package com.wz.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of strings names of size n. You will create n folders in your file system such that,
 * at the ith minute, you will create a folder with the name names[i].
 * Since two files cannot have the same name, if you enter a folder name which is previously used,
 * the system will have a suffix addition to its name in the form of (k), where,
 * k is the smallest positive integer such that the obtained name remains unique.
 * Return an array of strings of length n where ans[i] is the actual name the system will assign to the ith folder when you create it.
 *
 * Example 1:
 * Input: names = ["wano","wano","wano","wano"]
 * Output: ["wano","wano(1)","wano(2)","wano(3)"]
 * Explanation: Just increase the value of k each time you create folder "wano".
 *
 * Example 5:
 * Input: names = ["kaido","kaido(1)","kaido","kaido(1)"]
 * Output: ["kaido","kaido(1)","kaido(2)","kaido(1)(1)"]
 * Explanation: Please note that system adds the suffix (k) to current name even it contained the same suffix before.
 *
 * Constraints:
 * 1. 1 <= names.length <= 5 * 10^4
 * 2. 1 <= names[i].length <= 20
 * 3. names[i] consists of lower case English letters, digits and/or round brackets.
 */
public class MakingFileNamesUnique {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getFolderNames(new String[]{"wano", "wano", "wano", "wano"})));
        System.out.println(Arrays.toString(getFolderNames(new String[]{"kaido", "kaido(1)", "kaido", "kaido(1)"})));
    }

    /**
     * 用 map 保存每个 name 出现的次数，再次遇到时在 name 加上其出现的次数保存到结果中
     */
    public static String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>(names.length);
        String[] result = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            String base = names[i], key = base;
            int j = map.getOrDefault(key, 1);
            while (map.containsKey(key)) {
                key = base + "(" + (j++) + ")";
            }
            map.put(base, j);
            map.put(key, 1);
            result[i] = key;
        }
        return result;
    }
}
