package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import romanToInteger.Solution;

public class SolutionTest {
  String str1 = "III";
  String str2 = "LVIII";
  String str3 = "MCMXCIV";
  String str4 = "flower";
  String str5 = "flow";
  String str6 = "flight";
  
  Solution solution1 = new Solution(str1);
  Solution solution2 = new Solution(str2);
  Solution solution3 = new Solution(str3);
  
  String[] strs = {str4, str5, str6};
  
  StringBuilder sb = new StringBuilder();
   
  @Test
  public void test() {
    /*     
    assertEquals(solution1.solve(), 3);
    assertEquals(solution2.solve(), 58);
    assertEquals(solution3.solve(), 1994);
    Arrays.sort(strs);
    System.out.println(strs[0]);
    System.out.println(strs[1]);
    System.out.println(strs[2]+"d");
    */
    sb.append("what does this mean?1");
    char last = sb.charAt(sb.length() - 1);
    sb.deleteCharAt(sb.length() - 1);
    assertEquals("what does this mean?", sb.toString());
    
  }

}
