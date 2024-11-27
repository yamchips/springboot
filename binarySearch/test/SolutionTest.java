

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import binarySearch.Solution;

public class SolutionTest {
  Solution solution = new Solution();
  
  @Test
  public void test2() {
    int[] nums = {1, 0, 3, 5, 9, 12};
    int target = 2;
    solution.searchInsert(nums, target);
  }
  
  @Test
  public void test1() {
    int[] nums = {1,3,4,2,4,5};
    assertEquals(4, solution.findDuplicate(nums));
  }
  
  //@Test
  public void test() {
    int[] nums1 = {-1,0,3,5,9,12};
    //assertEquals(4, solution.search(nums1, 9));
    assertEquals(-1, solution.search(nums1, 4));
    
    
  }

}
