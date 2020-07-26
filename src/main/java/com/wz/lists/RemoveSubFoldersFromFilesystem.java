package com.wz.lists;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a list of folders, remove all sub-folders in those folders and return in any order the folders after removing.
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
 * For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 *
 * Example 1:
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * Explanation: Folders "/a/b/" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
 *
 * Example 2:
 * Input: folder = ["/a","/a/b/c","/a/b/d"]
 * Output: ["/a"]
 * Explanation: Folders "/a/b/c" and "/a/b/d/" will be removed because they are subfolders of "/a".
 *
 * Example 3:
 * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 */
public class RemoveSubFoldersFromFilesystem {
    public static void main(String[] args) {
        String[] folder = new String[]{"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"};
        System.out.println(removeSubfolders(folder));

        folder = new String[]{"/a", "/a/b/c", "/a/b/d"};
        System.out.println(removeSubfolders(folder));

        folder = new String[]{"/a/b/c", "/a/b/ca", "/a/b/d"};
        System.out.println(removeSubfolders(folder));
    }

    /**
     * 按照字母序和长度从小到大排列，这样相类似的全部在一起，只要后面的不是 start with 之前的，就不是subfolder，否则就是subfolder
     */
    public static List<String> removeSubfolders(String[] folder) {
        LinkedList<String> result = new LinkedList<>();
        Arrays.parallelSort(folder);
        for (String f : folder) {
            if (result.isEmpty() || !f.startsWith(result.peekLast() + "/")) {
                result.add(f);
            }
        }

        return result;
    }
}
