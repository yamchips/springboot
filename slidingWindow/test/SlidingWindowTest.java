

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import slidingWindow.SlidingWindow;

public class SlidingWindowTest {
  SlidingWindow solution1 = new SlidingWindow();
  
  @Test
  public void testLongestSubString() {
    String s1 = "aaabb";
    String s2 = "ababbcdab";
    int k = 3;
    int res = solution1.longestSubstring(s1, k);
    assertEquals(res, 3);
    assertEquals(solution1.longestSubstring(s2, 2), 5);
  }
  
  @Test
  public void testMin() {
    String s1 = "DAOBECODEBANC";
    String t1 = "ABC";
    solution1.minWindow(s1, t1);
//    System.out.println(solution1.minWindow5(s1, t1));
    assertTrue("BANC".equals(solution1.minWindow4(s1, t1)));
    String s2 = "aaaaabbbbbcdd";
    String t2 = "abcdd";
    solution1.minWindow4(s2, t2);
//    System.out.println(solution1.minWindow5(s2, t2));
    assertTrue("abbbbbcdd".equals(solution1.minWindow4(s2, t2)));
    
    String s3 = "zechiwcmjktroasetkzxlxpdibkeiqdhuhqfdsrmfmfvny";
    String t3 = "ziiahux";
    solution1.minWindow4(s3, t3);
    assertTrue("asetkzxlxpdibkeiqdhu".equals(solution1.minWindow4(s3, t3)));
    
    String s4 = "ADOBECODEBANC";
    solution1.minWindow4(s4, t1);
    assertTrue("BANC".equals(solution1.minWindow4(s4, t1)));
  }
  
  @Test
  public void testMax() {
    int[] nums = {1,3,-1,5,-3,3,6,7};
    int k = 4;
    solution1.maxSlidingWindow(nums, k);
  }
  
  @Test
  public void test() {
    String s1 = "adc";
    String s2 = "dcda";
    assertEquals(true, solution1.checkInclusion(s1, s2));
  }
  
  @Test
  public void test1() {
    String s = "eADOBECODEBANC";
    String t = "ABC";
    assertEquals(true, "BANC".equals(solution1.minWindow3(s, t)));
  }
  
  @Test
  public void test2() {
    String s = "abc";
    String t = "acb";
    assertTrue("abc".equals(solution1.minWindow(s, t)));
  }
  
  @Test
  public void test3() {
    String s1 = "adefgdicb";
    assertEquals(7, solution1.lengthOfLongestSubstring(s1));
    String s2 = "pwwkew";
    assertEquals(3,solution1.lengthOfLongestSubstring(s2));
    String s3 = "abcdefg";
    assertEquals(7, solution1.lengthOfLongestSubstring(s3));
    String s4 = "abcabcbb";
    assertEquals(3, solution1.lengthOfLongestSubstring(s4));
    
        
  }
}
