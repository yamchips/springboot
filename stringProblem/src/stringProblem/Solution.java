package stringProblem;

import java.util.Stack;

public class Solution {
  public boolean isValid(String s) {
      Stack<Character> stack = new Stack<>();

      for (int i = 0; i < s.length(); i++) {
          if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
              stack.push(s.charAt(i));
          } else {
              if (!stack.isEmpty()) {
                  Character cha = stack.pop();
                  Boolean res = compareChars(cha, s.charAt(i));
                  if (res == false) {
                      return res;
                  }
              } else {
                  return false;
              }
          }

      }
      return stack.isEmpty() ? true : false;
  }

  private boolean compareChars(Character char1, Character char2) {
      
    Boolean res = switch (char1) {
          case '(' -> char2 == ')';
          case '[' -> char2 == ']';
          case '{' -> char2 == '}';
          default -> false;
      } ;
      return res;
  }
}
