package anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

  public List<Integer> findAnagrams2(String s, String p) {
    /**
     * 
     
    StringBuilder window = new StringBuilder();

    window.append(s.substring(0, p.length()));
    List<Integer> res = new ArrayList<>();
    
    for (int j = 0; j <= s.length() - p.length(); j++) {
        boolean flag = true;
        StringBuilder origin = new StringBuilder(p);
        for (int i = 0; i < p.length(); i++) {
            if (!origin.toString().contains(String.valueOf(window.toString().charAt(i)))) {
                flag = false;
                break;
            } else {
                int index = origin.indexOf(String.valueOf(window.toString().charAt(i)));
                origin.deleteCharAt(index);
            }
        }
        if (flag && origin.length() == 0) res.add(j);
        window.deleteCharAt(0);
        if (j ==  s.length() - p.length()) break;
        window.append(s.charAt(j + p.length()));
    }
    return res;
    */
    List<Integer> ans = new ArrayList<>();
    if (p.length() > s.length()) {
        return ans;
    }

    int[] pFreq = new int[26];
    int[] sFreq = new int[26];
    for (int i = 0; i < p.length(); i++) {
        pFreq[p.charAt(i) - 'a']++;
        sFreq[s.charAt(i) - 'a']++;
    }

    if (Arrays.equals(pFreq, sFreq)) {
        ans.add(0);
    }

    int i = 0, j = p.length();
    while (j < s.length()) {
        sFreq[s.charAt(i++) - 'a']--;
        sFreq[s.charAt(j++) - 'a']++;
        if (Arrays.equals(sFreq, pFreq)) {
            ans.add(i);
        }
    }

    return ans;
    
  }
  
  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> res = new ArrayList<>();
    if (s.length() < p.length()) return res;
    
    int[] sFreq = new int[26];
    int[] pFreq = new int[26];
    
    for (int i = 0; i < p.length(); i++) {
      sFreq[s.charAt(i) - 'a']++;
      pFreq[p.charAt(i) - 'a']++;
    }
    
    
    for (int i = 0; i <= s.length() - p.length(); i++) {
      if (Arrays.equals(sFreq, pFreq))
        res.add(i);
      sFreq[s.charAt(i) - 'a']--;
      if (i == s.length() - p.length())
        break;
      int index = s.charAt(i + p.length()) - 'a';
      sFreq[index]++;
    }
    
    return res;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
