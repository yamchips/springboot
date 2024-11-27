package binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
  
  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(6);
    list.add(1);
    list.add(8);
    list.add(4);
    list.add(4);
    list.add(4);
    Collections.sort(list);
    System.out.printf("list is %s\n", list.toString());
    int index = Collections.binarySearch(list, 5);
    //System.out.println(-index-1);
    if (index < 0) index = - index - 1;
    System.out.println(index);
  }
  
  public int findDuplicate(int[] nums) {
    int low = 1, high = nums.length - 1;
    while (low != high) {
      int mid = (low + high) / 2;
      int count = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] <= mid) {
          count++;
        }
      }
      if (count > mid) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }
  
  public int searchInsert(int[] nums, int target) {
    int start = 0;
    int end = nums.length - 1;

    while (start <= end) {
      int mid = (end + start) / 2;
      if (nums[mid] == target)
        return mid;
      else if (nums[mid] > target)
        end = mid - 1;
      else
        start = mid + 1;
    }

    return start;
  }
  
  /**
   * Recursive way of binary search.
   * @param nums
   * @param target
   * @return
   */
  public int search(int[] nums, int target) {
    int low = 0, high = nums.length;
    
    if (nums.length == 1) {
      return nums[0] == target ? 0 : -1;
    }
    
    if (nums[(high + low) / 2] > target) {
      int r1 = search(Arrays.copyOfRange(nums, low, (high + low) / 2), target);
      return r1 + low;
    } else if (nums[(high + low) / 2] == target) {
      return (high + low) / 2;
    } else {
      int r2 = search(Arrays.copyOfRange(nums, (high + low) / 2, high), target);
      return r2 + (high + low) /2;
    }

  }
}
