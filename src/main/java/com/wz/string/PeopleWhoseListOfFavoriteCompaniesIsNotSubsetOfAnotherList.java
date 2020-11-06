package com.wz.string;

import java.util.*;

/**
 * Given the array favoriteCompanies where favoriteCompanies[i] is the list of favorites companies for the ith person (indexed from 0).
 * Return the indices of people whose list of favorite companies is not a subset of any other list of favorites companies.
 * You must return the indices in increasing order.
 *
 * Example 1:
 * Input: favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
 * Output: [0,1,4]
 * Explanation:
 * Person with index=2 has favoriteCompanies[2]=["google","facebook"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] corresponding to the person with index 0.
 * Person with index=3 has favoriteCompanies[3]=["google"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] and favoriteCompanies[1]=["google","microsoft"].
 * Other lists of favorite companies are not a subset of another list, therefore, the answer is [0,1,4].
 *
 * Example 2:
 * Input: favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
 * Output: [0,1]
 * Explanation: In this case favoriteCompanies[2]=["facebook","google"] is a subset of favoriteCompanies[0]=["leetcode","google","facebook"], therefore, the answer is [0,1].
 *
 * Constraints:
 * 1. 1 <= favoriteCompanies.length <= 100
 * 2. 1 <= favoriteCompanies[i].length <= 500
 * 3. 1 <= favoriteCompanies[i][j].length <= 20
 * 4. All strings in favoriteCompanies[i] are distinct.
 * 5. All lists of favorite companies are distinct, that is, If we sort alphabetically each list then favoriteCompanies[i] != favoriteCompanies[j].
 * 6. All strings consist of lowercase English letters only.
 */
public class PeopleWhoseListOfFavoriteCompaniesIsNotSubsetOfAnotherList {
    public static void main(String[] args) {
        List<List<String>> favoriteCompanies = new ArrayList<>();
        favoriteCompanies.add(Arrays.asList("leetcode", "google", "facebook"));
        favoriteCompanies.add(Arrays.asList("leetcode", "amazon"));
        favoriteCompanies.add(Arrays.asList("facebook", "google"));
        System.out.println(peopleIndexes(favoriteCompanies));
    }

    public static List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < favoriteCompanies.size(); i++) {
            set.add(i);
            for (int j = 0; j < favoriteCompanies.size(); j++) {
                if (j == i) {
                    continue;
                }
                Set<String> a = new HashSet<>(favoriteCompanies.get(i));
                Set<String> b = new HashSet<>(favoriteCompanies.get(j));
                if (b.containsAll(a)) {
                    set.remove(i);
                }
            }
        }

        List<Integer> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }
}
