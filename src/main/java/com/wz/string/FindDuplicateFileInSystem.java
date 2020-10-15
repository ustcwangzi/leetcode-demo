package com.wz.string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given a list of directory info including directory path, and all the files with contents in this directory,
 * you need to find out all the groups of duplicate files in the file system in terms of their paths.
 * A group of duplicate files consists of at least two files that have exactly the same content.
 * A single directory info string in the input list has the following format:
 * "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
 * It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively)
 * in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.
 * The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content.
 * A file path is a string that has the following format:
 * "directory_path/file_name.txt"
 *
 * Example 1:
 * Input:
 * ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
 * Output:
 * [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
 */
public class FindDuplicateFileInSystem {
    public static void main(String[] args) {
        System.out.println(findDuplicate(new String[]{"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"}));
    }

    /**
     * 把路径、文件名、文件内容从字符串中拆出来，建立一个文件内容和文件路径加文件名的映射，保存在 map 中
     * 遍历 map，哪些映射的元素个数多于 1 个，说明有重复文件
     */
    public static List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] split = path.split(" ");
            String folder = split[0];
            for (int i = 1; i < split.length; i++) {
                String content = split[i].substring(split[i].indexOf("("));
                map.putIfAbsent(content, new LinkedList<>());
                map.get(content).add(folder + "/" + split[i].substring(0, split[i].indexOf("(")));
            }
        }

        List<List<String>> result = new LinkedList<>();
        for (List<String> val : map.values()) {
            if (val.size() > 1) {
                result.add(val);
            }
        }
        return result;
    }
}
