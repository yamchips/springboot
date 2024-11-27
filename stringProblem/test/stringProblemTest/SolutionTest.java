package stringProblemTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import stringProblem.Solution;

public class SolutionTest {
  private Solution sol1;
  private String case1;
  private String case2;
  private String case3;
  private String case4;
  private String case5;
  private String case6;

  
  @Before
  public void setup() {
    sol1 = new Solution();
    case1 = "()";
    case2 = "())";
    case3 = "(]";
    case4 = ")()";
    case5 = "()[]";
    case6 = "()[";
  }
  /*
   * 
   */
  @Test
  public void test() {
    assertEquals(sol1.isValid(case1), true);
    assertEquals(sol1.isValid(case2), false);
    assertEquals(sol1.isValid(case3), false);
    assertEquals(sol1.isValid(case4), false);
    assertEquals(sol1.isValid(case5), true);
    assertEquals(sol1.isValid(case6), false);
  }

}
