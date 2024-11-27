

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import priorityQueue.Solution;

public class SolutionTest {
  int[] input1 = {1,3,-1,-3,5,3,6,7};
  int[] output1 = {3,3,5,5,6,7};
  int[] input2 = {1, -1};
  int[] output2 = {1};
  Solution solution1 = new Solution();
  
  @Test
  public void test() {
//    for (int i = 0; i < output1.length; i++) {
//      assertEquals(output1[i], solution1.maxSlidingWindow(input1, 3)[i]);
//    }
    assertEquals(output2[0], solution1.maxSlidingWindow(input2, 1)[0]);
  }

}
