package com.wz.string;

/**
 * Given a string IP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros.
 * For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses but "192.168.01.1", while "192.168.1.00" and "192.168@1.1" are invalid IPv4 addresses.
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 * 1. 1 <= xi.length <= 4
 * 2. xi is a hexadecimal string which may contain digits, lower-case English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
 * 3. Leading zeros are allowed in xi.
 * For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses,
 * while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.
 *
 * Example 1:
 * Input: IP = "172.16.254.1"
 * Output: "IPv4"
 * Explanation: This is a valid IPv4 address, return "IPv4".
 *
 * Example 2:
 * Input: IP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * Output: "IPv6"
 * Explanation: This is a valid IPv6 address, return "IPv6".
 *
 * Example 3:
 * Input: IP = "256.256.256.256"
 * Output: "Neither"
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 */
public class ValidateIPAddress {
    public static void main(String[] args) {
        System.out.println(validIPAddress("172.16.254.1"));
        System.out.println(validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
        System.out.println(validIPAddress("256.256.256.256"));
    }

    public static String validIPAddress(String IP) {
        // Checking IP length , checking for edge cases where last/first char is . / :
        if (IP.length() > 0 && (IP.charAt(0) == '.' || IP.charAt(IP.length() - 1) == '.' ||
                IP.charAt(0) == ':' || IP.charAt(IP.length() - 1) == ':')) {
            return "Neither";
        }

        String[] array = IP.split("\\.");
        // If it is not an IPv4 address
        if (array.length != 4) {
            array = IP.split(":");
            if (array.length != 8) {
                return "Neither";
            }
            try {
                for (String s : array) {
                    if (s.length() < 1 || s.length() > 4) {
                        return "Neither";
                    }
                    Integer.parseInt(s, 16);
                }
            } catch (Exception e) {
                return "Neither";
            }
            return "IPv6";
        } else {
            // If it is an IPv4 address
            try {
                for (String s : array) {
                    if (s.length() < 1 || s.length() > 3 || (s.charAt(0) == '0' && s.length() > 1)) {
                        return "Neither";
                    }
                    int j = Integer.parseInt(s);
                    if (j < 0 || j > 255) {
                        return "Neither";
                    }
                }
            } catch (Exception e) {
                return "Neither";
            }
            return "IPv4";
        }
    }
}
