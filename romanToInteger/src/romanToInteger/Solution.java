package romanToInteger;

import java.util.HashMap;
import java.util.Map;

public class Solution {
  private String str;
  private int res;
  
  public Solution(String s) {
    this.str = s;
    this.res = 0;
  }
  
  public int solve() {
    String[] target = this.str.split("(?!^)");
    
    for (int i = 0; i < target.length; i++) {
      switch (target[i]) {
        case "I":
          if (i < target.length -1) {
            if ("V".equals(target[i+1])) {
              this.res += 4;
              i += 1;
            } else if ("X".equals(target[i+1])) {
              this.res += 9;
              i += 1;
            } else {
              this.res += 1;
            }
          } else {
            this.res += 1;
          }
          break;
        case "V":
          this.res += 5;
          break;
        case "X":
          if (i < target.length -1) {
            if ("L".equals(target[i+1])) {
              this.res += 40;
              i += 1;
            } else if ("C".equals(target[i+1])) {
              this.res += 90;
              i += 1;
            } else {
              this.res += 10;
            }
          } else {
            this.res += 10;
          }
          break;
        case "L":
          this.res += 50;
          break;
        case "C":
          if (i < target.length -1) {
            if ("D".equals(target[i+1])) {
              this.res += 400;
              i += 1;
            } else if ("M".equals(target[i+1])) {
              this.res += 900;
              i += 1;
            } else {
              this.res += 100;
            }
          } else {
            this.res += 100;
          }
          break;
        case "D":
          this.res += 500;
          break;
        case "M":
          this.res += 1000;
          break;
        default:
          break;
      }
    }
    return this.res;
  }
    
  public int solve2(String s) {
    Map<Character, Integer> m = new HashMap<>();
    
    m.put('I', 1);
    m.put('V', 5);
    m.put('X', 10);
    m.put('L', 50);
    m.put('C', 100);
    m.put('D', 500);
    m.put('M', 1000);
    
    int ans = 0;
    
    for (int i = 0; i < s.length(); i++) {
        if (i < s.length() - 1 && m.get(s.charAt(i)) < m.get(s.charAt(i + 1))) {
            ans -= m.get(s.charAt(i));
        } else {
            ans += m.get(s.charAt(i));
        }
    }
    
    return ans;
    
  }

}
