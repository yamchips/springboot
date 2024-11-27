package slidingWindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SlidingWindow {
  public int longestSubstring(String s, int k) {
    if (s == null || s.length() == 0 || s.length() < k)
      return 0;
    int[] freq = new int[26];
    // record the frequency of each character
    for (int i = 0; i < s.length(); i += 1)
      freq[s.charAt(i) - 'a'] += 1;
    boolean flag = true;
    for (int i = 0; i < freq.length; i += 1) {
      if (freq[i] < k && freq[i] > 0)
        flag = false;
    }
    // return the length of string if this string is a valid string
    if (flag == true)
      return s.length();
    int result = 0;
    int start = 0, cur = 0;
    // otherwise we use all the infrequent elements as splits
    while (cur < s.length()) {
      if (freq[s.charAt(cur) - 'a'] < k) {
        result = Math.max(result, longestSubstring(s.substring(start, cur), k));
        start = cur + 1;
      }
      cur++;
    }
    result = Math.max(result, longestSubstring(s.substring(start), k));
    return result;
  }

  public int[] maxSlidingWindow(int[] nums, int k) {
    int[] res = new int[nums.length - k + 1];
    Deque<Integer> index = new ArrayDeque<>();

    int j = 0;
    for (int i = 0; i < nums.length; i++) {
      while (!index.isEmpty() && nums[index.peekLast()] < nums[i]) {
        index.pollLast();
      }
      if (!index.isEmpty() && index.peekFirst() < i - k + 1) {
        index.pollFirst();
      }
      index.offerLast(i);
      if (i >= k - 1)
        res[j++] = nums[index.peekFirst()];
    }
    return res;
  }

  public int lengthOfLongestSubstring(String s) {
    int i = 0, j = 0, max = 0;
    Set<Character> set = new HashSet<>();

    while (j < s.length()) {
      if (!set.contains(s.charAt(j))) {
        set.add(s.charAt(j++));
        max = Math.max(max, set.size());
      } else {
        set.remove(s.charAt(i++));
      }
    }

    return max;
  }

  public int lengthOfLongestSubstring1(String s) {
    if (s.length() <= 1)
      return s.length();
    int[] window = new int[128];
    int start = 0, maxLength = 0;
    for (int end = 0; end < s.length(); end++) {
      window[s.charAt(end)]++;
      if (window[s.charAt(end)] > 1) {
        if (maxLength < end - start) {
          maxLength = end - start;
        }
        // find the index of the character that is same with current one
        start = findHelper(s.substring(start, end), s.charAt(end)) + start + 1;
        Arrays.fill(window, 0);
        window[s.charAt(start)]++;
        end = start;
      }
    }
    int count = 0;
    for (Integer j : window) {
      if (j > 0)
        count++;
    }
    return count > maxLength ? count : maxLength;
  }

  private int findHelper(String s, Character c) {
    char[] array = s.toCharArray();
    for (int i = 0; i < array.length; i++) {
      if (array[i] == c) {
        return i;
      }
    }
    return 0;
  }

  public String minWindow5(String s, String t) {
    if (s.length() < t.length())
      return "";
    // create and initialize an int[] and a map to represent t
    int[] target = new int[58];
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      target[t.charAt(i) - 'A']++;
      map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
    }
    int start = 0, end = t.length() - 1;
    // create window
    int[] window = new int[58];
    // determine whether there is one char in s that matches t
    boolean match = false;
    for (int i = 0; i < s.length(); i++) {
      if (map.containsKey(s.charAt(i))) {
        start = i;
        end = start + t.length() - 1;
        match = true;
        break;
      }
    }
    if (!match)
      return "";
    // if there is at least one match in s, initialize window
    for (int i = start; i <= end; i++) {
      window[s.charAt(i) - 'A']++;
    }
    int minWinLength = 0, curWinLength = 0, minStart = 0, minEnd = 0;
    while (start <= s.length() - t.length() && end < s.length()) {
      // determine whether we find a solution
      if (compare(window, target) >= 0) {
        curWinLength = end - start + 1;
        if (minWinLength == 0 || minWinLength > curWinLength) {
          minWinLength = curWinLength;
          minStart = start;
          minEnd = end;
        }
        // move start until value in target is exactly the value in window
        do {
          int before = compare(window, target);
          window[s.charAt(start) - 'A']--;
          start++;
          int after = compare(window, target);
          if (start == s.length() - t.length())
            break;
          if (before - after > 1)
            break;
        } while ((compare(window, target) > 0 || !map.containsKey(s.charAt(start))));

        if (compare(window, target) >= 0) {
          curWinLength = end - start + 1;
          if (minWinLength == 0 || minWinLength > curWinLength) {
            minWinLength = curWinLength;
            minStart = start;
            minEnd = end;
          }
        } else {
          start--;
          window[s.charAt(start) - 'A']++;
          if (compare(window, target) >= 0) {
            curWinLength = end - start + 1;
            if (minWinLength == 0 || minWinLength > curWinLength) {
              minWinLength = curWinLength;
              minStart = start;
              minEnd = end;
            }
          }

        }
      }
      // move end until we meet another match
      do {
        end++;
        if (end == s.length())
          break;
        window[s.charAt(end) - 'A']++;
      } while (!map.containsKey(s.charAt(end)));

    }

    return minWinLength == 0 ? "" : s.substring(minStart, minEnd + 1);
  }

  /*
   * Compare window and target. If there exists one index i that target[i] is not
   * 0 and target[i] > window[i], return -1. If no such index is found, then check
   * if there exists one index i such that target[i] < window[i], if so return 1.
   * Otherwise, every non-zero element in target equals to the corresponding
   * element in window, returns 0.
   */
  private int compare(int[] window, int[] target) {
    boolean allEqual = true;
    for (int i = 0; i < target.length; i++) {
      if (target[i] != 0) {
        if (target[i] > window[i]) {
          return -1;
        } else if (target[i] < window[i]) {
          allEqual = false;
        }
      }
    }
    return allEqual ? 0 : 1;
  }

  /**
   * Very neat solution. Refer to redtesla's reply in the top answer. It is
   * slightly different when we update target array.
   * https://leetcode.com/problems/minimum-window-substring/solutions/26808/here-is-a-10-line-template-that-can-solve-most-substring-problems/comments/25861
   * 
   * @param s
   * @param t
   * @return
   */
  public String minWindow(String s, String t) {
    if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
      return new String();
    }
    int[] target = new int[128];
    int count = t.length();
    int start = 0, end = 0, minLen = Integer.MAX_VALUE, startIndex = 0;
    // initialize an int[] array called target to represent String t
    for (char c : t.toCharArray()) {
      target[c]++;
    }
    char[] source = s.toCharArray();
    while (end < source.length) {
      /*
       * if a char in s exists in t, decrease counter We decrease counter only when
       * target[source[end]] > 0, because we might meet situation like aaaaaabbcdd and
       * abbcdd. target[a] will be a large negative number.
       */
      if (target[source[end]] > 0) {
        count--;
      }
      // update target array and move end pointer
      target[source[end]]--;
      end++;
      /*
       * when we find a solution, move start pointer to find a smaller window In
       * initial target array, chars in String t are positive number, then we move end
       * to find a window that satisfies our requirement. At this time, chars not in
       * String t are negative, chars in String t are zero or negative. 1. 0 means the
       * window contains the exact number of char we want. 2. negative means the
       * window contains more number of char than we want. e.g. -2 means we have two
       * more chars in the window, it could be the char in String t it also could be a
       * char that is not in String t 3. positive means the window still need this
       * number of char, positive value only exists in chars that is in String t For
       * chars not in String t, the value is negative or zero For chars in String t,
       * the value can be negative, positive or zero When we move start pointer, we
       * increase the value in target array. We determine if target[char] is zero
       * first (important!), Then we increase its value.
       */
      while (count == 0) {
        /*
         * update startIndex and minLen It's important to update first in case we meet
         * aaaabbcdd and abbcdd.
         */
        if (end - start < minLen) {
          startIndex = start;
          minLen = end - start;
        }
        /*
         * Q: What if source[start] is a char that is not in t? A: It is initialized as
         * 0, in line 213 we subtract it to a negative number. When It is increased to
         * 0, we move start pointer forward at once, so we won't trigger count++ because
         * of a char not in String t. If target[source[start]] is 0, then we definitely
         * meet a char that is in t. So, we add count, and exit this while loop. N: It's
         * important to run if block first then update target array. Q: What about the
         * case: aaaaaaabbcdd and abbcdd? A: target[a] will be a negative number due to
         * line 213. N: Here we meet if first, so the condition is == 0, if we update
         * target first, then the condition should be > 0, as shown in redtesla's
         * solution.
         */
        if (target[source[start]] == 0) {
          count++;
        }
        target[source[start]]++;
        start++;
      }
    }
    return minLen == Integer.MAX_VALUE ? new String() : new String(source, startIndex, minLen);
  }

  /**
   * My solution after understanding 3.
   * 
   * @param s
   * @param t
   * @return
   */
  public String minWindow4(String s, String t) {
    if (s.length() < t.length()) {
      return "";
    }
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
    }
    int count = 0, start = 0, minLength = 2 * s.length(), minStart = 0;
    // move end pointer
    for (int end = 0; end < s.length(); end++) {
      if (map.containsKey(s.charAt(end))) {
        if (map.get(s.charAt(end)) > 0) {
          count++;
        }
        map.put(s.charAt(end), map.get(s.charAt(end)) - 1);
      }
      if (count == t.length()) {
        while (!map.containsKey(s.charAt(start)) || map.get(s.charAt(start)) < 0) {
          if (map.containsKey(s.charAt(start))) {
            map.put(s.charAt(start), map.get(s.charAt(start)) + 1);
          }
          start++;
        }
        if (minLength > end - start + 1) {
          minLength = end - start + 1;
          minStart = start;
        }
        // when we exit while loop above, we must meet a char that is in String t and
        // its value in map is 0
        // so, we can directly set the corresponding value to 1
        // map.put(s.charAt(start), map.get(s.charAt(start)) + 1);
        map.put(s.charAt(start), 1);
        start++; // here we only move start one step forward, we can also don't move start
                 // no, because we update the map, so we need to move start, otherwise the logic
                 // is not right
                 // we need to reserve update map and increasing start at the same time
        count--;
      }
    }
    return minLength == 2 * s.length() ? "" : s.substring(minStart, minStart + minLength);
  }

  /**
   * Solution offered by others on Leetcode.
   * 
   * @param s String that will be searched
   * @param t Target string
   * @return substring of s that contains each character in t
   */
  public String minWindow3(String s, String t) {
    if (s.length() < t.length()) {
      return "";
    }
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
    }
    int count = 0, start = 0, min_length = Integer.MAX_VALUE, min_start = 0;
    for (int end = 0; end < s.length(); end++) {
      if (map.containsKey(s.charAt(end))) {
        if (map.get(s.charAt(end)) > 0) {
          count++;
        } else {
          System.out.println();
        }
        map.put(s.charAt(end), map.get(s.charAt(end)) - 1);
      }
      if (count == t.length()) {
        while (start < end && (!map.containsKey(s.charAt(start)) || map.get(s.charAt(start)) < 0)) {
          if (map.containsKey(s.charAt(start))) {
            map.put(s.charAt(start), map.get(s.charAt(start)) + 1);
          }
          start++;
        }
        if (min_length > end - start + 1) {
          min_length = end - (min_start = start) + 1;
        }
        if (map.containsKey(s.charAt(start))) {
          map.put(s.charAt(start), map.get(s.charAt(start)) + 1);
        }
        count--;
        start++;
      }
    }
    return min_length == Integer.MAX_VALUE ? "" : s.substring(min_start, min_start + min_length);

  }

  /**
   * minWindow solution2. Starting with a window whose length is t.length(), go
   * through the string s to find whether it is acceptable, if not, add its length
   * by 1 until we find the solution or return an empty string.
   * 
   * @param s String that we search in
   * @param t Target string
   * @return target substring or an empty string
   */
  public String minWindow2(String s, String t) {
    if (s.length() < t.length())
      return "";
    // use frequency array to represent t
    int[] freq = new int[58];

    for (int i = 0; i < t.length(); i++) {
      freq[t.charAt(i) - 'A']++;
    }
    int[] temp = new int[58];
    // the length of the window ranges from t.length() to s.length()
    for (int j = t.length(); j <= s.length(); j++) {
      // create and move the window through string s
      for (int k = 0; k < s.length(); k++) {
        temp[s.charAt(k) - 'A']++;
        if (k >= j)
          temp[s.charAt(k - j) - 'A']--;
        if (compareArrays(freq, temp))
          return s.substring(k - j + 1, k + 1);
      }
      Arrays.fill(temp, 0);
    }
    return "";
  }

  public String minWindow1(String s, String t) {
    if (s.length() < t.length())
      return "";
    // use frequency array to represent t
    int[] lowerFreq = new int[26];
    int[] upperFreq = new int[26];
    for (int i = 0; i < t.length(); i++) {
      if (Character.isLowerCase(t.charAt(i))) {
        lowerFreq[t.charAt(i) - 'a']++;
      } else {
        upperFreq[t.charAt(i) - 'A']++;
      }
    }
    int[] lowerTemp = new int[26];
    int[] upperTemp = new int[26];
    // the length of the window ranges from t.length() to s.length()
    for (int j = t.length(); j <= s.length(); j++) {
      // create and move the window through string s
      for (int k = 0; k < s.length(); k++) {
        if (Character.isLowerCase(s.charAt(k))) {
          lowerTemp[s.charAt(k) - 'a']++;
        } else {
          upperTemp[s.charAt(k) - 'A']++;
        }
        if (k >= j) {
          if (Character.isLowerCase(s.charAt(k - j))) {
            lowerTemp[s.charAt(k - j) - 'a']--;
          } else {
            upperTemp[s.charAt(k - j) - 'A']--;
          }
        }
        if (compareArrays(upperFreq, upperTemp) && compareArrays(lowerFreq, lowerTemp))
          return s.substring(k - j + 1, k + 1);
      }
      Arrays.fill(lowerTemp, 0);
      Arrays.fill(upperTemp, 0);
    }
    return "";
  }

  private boolean compareArrays(int[] freq, int[] temp) {
    for (int i = 0; i < freq.length; i++) {
      if (freq[i] != 0 && temp[i] < freq[i])
        return false;
    }
    return true;
  }

  public boolean checkInclusion(String s1, String s2) {
    if (s1.length() > s2.length())
      return false;
    Deque<Character> window = new ArrayDeque<>();
    int[] freq = new int[26];
    int[] temp = new int[26];
    // initialize freq and temp
    for (int i = 0; i < s1.length(); i++) {
      freq[s1.charAt(i) - 'a']++;
      temp[s2.charAt(i) - 'a']++;
    }
    if (Arrays.equals(freq, temp))
      return true;

    for (int i = s1.length(); i < s2.length(); i++) {

      temp[s2.charAt(i) - 'a']++;
      temp[s2.charAt(i - s1.length()) - 'a']--;
      if (Arrays.equals(freq, temp))
        return true;
    }
    return false;
  }
}
