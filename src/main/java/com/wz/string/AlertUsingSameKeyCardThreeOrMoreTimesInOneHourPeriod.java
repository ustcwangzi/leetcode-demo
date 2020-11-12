package com.wz.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * LeetCode company workers use key-cards to unlock office doors. Each time a worker uses their key-card,
 * the security system saves the worker's name and the time when it was used.
 * The system emits an alert if any worker uses the key-card three or more times in a one-hour period.
 * You are given a list of strings keyName and keyTime where [keyName[i], keyTime[i]]
 * corresponds to a person's name and the time when their key-card was used in a single day.
 * Access times are given in the 24-hour time format "HH:MM", such as "23:51" and "09:49".
 * Return a list of unique worker names who received an alert for frequent keycard use. Sort the names in ascending order alphabetically.
 * Notice that "10:00" - "11:00" is considered to be within a one-hour period, while "22:51" - "23:52" is not considered to be within a one-hour period.
 *
 * Example 1:
 * Input: keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"], keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
 * Output: ["daniel"]
 * Explanation: "daniel" used the keycard 3 times in a one-hour period ("10:00","10:40", "11:00").
 *
 * Example 2:
 * Input: keyName = ["leslie","leslie","leslie","clare","clare","clare","clare"], keyTime = ["13:00","13:20","14:00","18:00","18:51","19:30","19:49"]
 * Output: ["clare","leslie"]
 *
 * Constraints:
 * 1. 1 <= keyName.length, keyTime.length <= 105
 * 2. keyName.length == keyTime.length
 * 3. keyTime[i] is in the format "HH:MM".
 * 4. [keyName[i], keyTime[i]] is unique.
 * 5. 1 <= keyName[i].length <= 10
 * 6. keyName[i] contains only lowercase English letters.
 */
public class AlertUsingSameKeyCardThreeOrMoreTimesInOneHourPeriod {
    public static void main(String[] args) {
        String[] keyName = new String[]{"daniel", "daniel", "daniel", "luis", "luis", "luis", "luis"};
        String[] keyTime = new String[]{"10:00", "10:40", "11:00", "09:00", "11:00", "13:00", "15:00"};
        System.out.println(alertNames(keyName, keyTime));
    }

    public static List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<String>> map = new HashMap<>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(keyName[i], l -> new ArrayList<>()).add(keyTime[i]);
        }
        return map.entrySet().stream()
                .filter(AlertUsingSameKeyCardThreeOrMoreTimesInOneHourPeriod::isThreeTimesUsageInHour)
                .map(Map.Entry::getKey)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    private static boolean isThreeTimesUsageInHour(Map.Entry<String, List<String>> entry) {
        entry.getValue().sort(String::compareTo);
        for (int i = 1; i < entry.getValue().size() - 1; i++) {
            int difHour = Integer.parseInt(entry.getValue().get(i + 1).substring(0, 2)) - Integer.parseInt(entry.getValue().get(i - 1).substring(0, 2));
            int difMin = Integer.parseInt(entry.getValue().get(i + 1).substring(3, 5)) - Integer.parseInt(entry.getValue().get(i - 1).substring(3, 5));
            if (difHour == 0 || difHour == 1 && difMin <= 0) {
                return true;
            }
        }
        return false;
    }
}
