package slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindowCompare {

  /*
   * My solution after understanding 3.*
   * 
   * @param s
   * 
   * @param t
   * 
   * @return
   */
  public String minWindow4(String s, String t) {
    if (s.length() < t.length()) {
      return "";
    }
    Map<Character, Integer> map = new HashMap<>();
    // map only contains char in String t
    for (int i = 0; i < t.length(); i++) {
      map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
    }
    int count = 0, start = 0, minLength = 2 * s.length(), minStart = 0;
    // move end pointer
    for (int end = 0; end < s.length(); end++) {
      // use count to determine whether we find a solution
      if (map.containsKey(s.charAt(end))) {
        if (map.get(s.charAt(end)) > 0) { // if it's <= 0 that means the char has appeared
                                          // more times than that in String t
          count++;
        }
        map.put(s.charAt(end), map.get(s.charAt(end)) - 1);
      }
      // if we find a solution, move start to find a smaller window
      if (count == t.length()) {
        /*
         * when start pointer points to a char not in map, move it or it points to a
         * char in map but the value is negative, move it
         */
        while (!map.containsKey(s.charAt(start)) || map.get(s.charAt(start)) < 0) {
          if (map.containsKey(s.charAt(start))) {
            map.put(s.charAt(start), map.get(s.charAt(start)) + 1);
          }
          start++;
        }
        // update minLength and minStart
        if (minLength > end - start + 1) {
          minLength = end - start + 1;
          minStart = start;
        }
        // update map and move start pointer again
        map.put(s.charAt(start), 1);
        start++;
        count--;
      }
    }
    return minLength == 2 * s.length() ? "" : s.substring(minStart, minStart + minLength);
  }

  public String minWindow(String s, String t) {
    if (s.length() < t.length())
      return "";
    int[] target = new int[128];
    int count = t.length();
    int start = 0, end = 0, minLen = Integer.MAX_VALUE, startIndex = 0;
    for (char c : t.toCharArray()) {
      target[c]++;
    }
    while (end < s.length()) {
      if (target[s.charAt(end)] > 0) {
        count--;
      }
      target[s.charAt(end)]--;
      end++;
      while (count == 0) {
        if (end - start < minLen) {
          startIndex = start;
          minLen = end - start;
        }
        if (target[s.charAt(start)] == 0) {
          count++;
        }
        target[s.charAt(start)]++;
        start++;
      }
    }
    return minLen == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLen);
  }

}
