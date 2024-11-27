package priorityQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
  public int[] maxSlidingWindow1(int[] a, int k) {   
    if (a == null || k <= 0) {
      return new int[0];
    }
    int n = a.length;
    int[] r = new int[n-k+1];
    int ri = 0;
    // store index
    Deque<Integer> q = new ArrayDeque<>();
    for (int i = 0; i < a.length; i++) {
      // remove numbers out of range k
      while (!q.isEmpty() && q.peek() < i - k + 1) {
        q.poll();
      }
      // remove smaller numbers in k range as they are useless
      while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
        q.pollLast();
      }
      // q contains index... r contains content
      q.offer(i);
      if (i >= k - 1) {
        r[ri++] = a[q.peek()];
      }
    }
    return r;
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
          if (i >= k - 1) res[j++] = nums[index.peekFirst()];
      }
      return res;
  }

}
