package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

  public static void main(String[] args) {
    // int[] nums = {10, 22, 9, 33, 21, 50, 41, 60};
    // int[] nums = {10,9,2,5,3,7,101,18};
    // Function call
    // System.out.println("Length of LIS is " + lengthOfLIS(nums));

    // uniquePaths(1, 2);
    // minDistance("intention", "execution");
    // maxSubArray2(new int[] { -2, -1, -3, -4, -1, -2, -1, -5, -4 });
    // wordBreak("cars", List.of("car","ca","rs"));
    // coinChange3(new int[] { 5, 2 }, 11);
    // canPartition(new int[] {1, 5, 11, 5});
    // canJump(new int[] {3,2,1,0,4});
    // longestPalindrome("babad");
    // maxProduct(new int[] { 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, -10, 10, 10,
    // 10, 10, 10, 10, 10,
    // 10, 10, 0 });
    trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 });
  }

  public static int trap(int[] height) {
    int n = height.length;
    if (n <= 2)
        return 0;
    // pre-compute
    int[] leftMax = new int[n];
    int[] rightMax = new int[n];
    leftMax[0] = height[0]; 
    rightMax[n - 1] = height[n - 1];
    for (int i = 1, j = n - 2; i < n; i++, j--) {
        leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        rightMax[j] = Math.max(rightMax[j + 1], height[j]);
    }
    System.out.println(Arrays.toString(leftMax));
    System.out.println(Arrays.toString(rightMax));
    int totalWater = 0;
    for (int k = 1; k < n - 1; ++k) { 
        int water = Math.min(leftMax[k - 1], rightMax[k + 1]) - height[k];
        totalWater += (water > 0) ? water : 0;
    }
    return totalWater;
  }

  public static int maxProduct(int[] nums) {
    int[] max = new int[nums.length];
    int[] min = new int[nums.length];
    int ans = nums[0];
    max[0] = nums[0];
    min[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > 0) {
        max[i] = max[i - 1] > 0 ? nums[i] * max[i - 1] : nums[i];
        min[i] = min[i - 1] > 0 ? Math.min(min[i - 1], nums[i]) : nums[i] * min[i - 1];
      } else {
        max[i] = min[i - 1] < 0 ? nums[i] * min[i - 1] : max[i - 1];
        min[i] = max[i - 1] <= 0 ? Math.min(min[i - 1], nums[i]) : nums[i] * max[i - 1];
      }
      ans = Math.max(ans, max[i]);
    }
    return ans;
  }

  public static String longestPalindrome(String s) {
    int n = s.length(), start = 0, end = 0, maxLength = 0;
    if (n == 1)
      return s;
    boolean[][] dp = new boolean[n][n];
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j <= n - 1; j++) {
        if (j - i <= 2) {
          if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = true;
          }
        } else {
          if (s.charAt(i) == s.charAt(j) || dp[i + 1][j - 1]) {
            dp[i][j] = true;
          }
        }
        if (dp[i][j] && j - i + 1 > maxLength) {
          maxLength = j - i + 1;
          start = i;
          end = j + 1;
        }

      }
    }

    return s.substring(start, end);
  }

  public static boolean canJump(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    Arrays.fill(dp, n + 1);
    dp[0] = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i; j < Math.min(n, i + nums[i]); j++) {
        dp[j] = Math.min(dp[j], 1 + dp[i]);
        if (j == n - 1)
          return true;
      }
    }
    if (dp[n - 1] == n + 1)
      return false;
    return true;
  }

  public static boolean canPartition(int[] nums) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    // if the sum is odd, then no such partition
    int mid = sum / 2;
    if (mid * 2 != sum)
      return false;
    // now the sum is even
    int n = nums.length;
    boolean[][] dp = new boolean[n + 1][mid + 1];
    dp[0][0] = true;
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j <= mid; j++) {
        if (j - nums[i - 1] >= 0) {
          dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    // System.arraycopy(nums, sum, dp, mid, n);
    return dp[n][mid];
  }

  public static int coinChange3(int[] coins, int amount) {
    Map<Integer, Integer> map = new HashMap<>();
    return coinChange(coins, amount, map);
  }

  private static int coinChange(int[] coins, int amount, Map<Integer, Integer> mem) {
    if (amount < 0)
      return -1;
    if (amount == 0)
      return 0;
    Integer c = mem.get(amount);
    if (c != null)
      return c;
    int cc = -1;
    for (int i = 0; i < coins.length; i++) {
      int coin = coinChange(coins, amount - coins[i], mem);
      if (coin >= 0)
        if (cc < 0) {
          cc = coin + 1;
        } else {
          cc = Math.min(cc, coin + 1);
        }
      // cc = cc < 0 ? coin + 1 : Math.min(cc, coin + 1);
    }
    mem.put(amount, cc);
    return cc;
  }

  public static int coinChange2(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;
    for (int i = 1; i <= amount; i++) {
      for (int j = 0; j < coins.length; j++) {
        if (i >= coins[j]) {
          dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        }
      }
    }
    return dp[amount] > amount ? -1 : dp[amount];
  }

  public static int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    int n = coins.length;
    // traverse the array
    for (int i = 1; i <= amount; i++) {
      int negCount = 0, minOneCount = 0;
      List<Integer> minList = new ArrayList<>();
      for (int j = 0; j < n; j++) {
        if (i - coins[j] < 0) {
          negCount += 1;
        } else {

          if (dp[i - coins[j]] == -1) {
            minOneCount += 1;
          } else {
            // dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
            minList.add(dp[i - coins[j]]);
          }

        }
      }
      if (negCount == n || minOneCount == n || negCount + minOneCount == n) {
        dp[i] = -1;
      } else {
        // System.out.println();
        int min = minList.get(0);
        for (int num : minList) {
          if (num < min) {
            min = num;
          }
        }
        dp[i] = 1 + min;
      }

    }
    return dp[amount];
  }

  public static boolean wordBreak(String s, List<String> wordDict) {
    int n = s.length();
    boolean[] dp = new boolean[n + 1];
    dp[0] = true;
    int maxLength = 0;
    for (String word : wordDict) {
      maxLength = Math.max(maxLength, word.length());
    }

    for (int i = 0; i < n; i++) {
      for (int j = i; j >= Math.max(i + 1 - maxLength, 0); j--) {
        String temp = s.substring(j, i + 1);
        if (dp[j] == true && wordDict.contains(temp)) {
          dp[i + 1] = true;
          break;
        }
      }
    }

    return dp[n];
  }

  public static int maxSubArray2(int[] nums) {
    int n = nums.length;
    int max = Integer.MIN_VALUE, sum = 0;
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      sum += nums[i];
      max = Math.max(sum, max);

      if (sum < 0) {
        sum = 0;
        res = new ArrayList<>();
      } else {
        res.add(nums[i]);
      }
    }

    return max;
  }

  public static int maxSubArray(int[] nums) {

    return helper(nums, 0, nums.length - 1);
  }

  public static int helper(int nums[], int i, int j) {

    if (i == j) {
      return nums[i];
    }

    int mid = (i + j) / 2;
    int sum = 0, leftMaxSUM = Integer.MIN_VALUE;

    for (int l = mid; l >= i; l--) {
      sum += nums[l];
      if (sum > leftMaxSUM) {
        leftMaxSUM = sum;
      }
    }

    int rightMaxSUM = Integer.MIN_VALUE;
    sum = 0; // reset sum to 0
    for (int l = mid + 1; l <= j; l++) {
      sum += nums[l];
      if (sum > rightMaxSUM) {
        rightMaxSUM = sum;
      }
    }

    int maxLeftRight = Math.max(helper(nums, i, mid), helper(nums, mid + 1, j));
    return Math.max(maxLeftRight, leftMaxSUM + rightMaxSUM);

  }

  static int minDistance(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    if (m == 0)
      return n;
    if (n == 0)
      return m;
    int[][] dp = new int[m][n];
    // initialize dp[0][0]
    if (word1.charAt(0) == word2.charAt(0)) {
      dp[0][0] = 0;
    } else {
      dp[0][0] = 1;
    }
    // initialize first row
    for (int i = 1; i < n; i++) {
      if (word1.charAt(0) == word2.charAt(i)) {
        dp[0][i] = dp[0][i - 1];
      } else {
        dp[0][i] = dp[0][i - 1] + 1;
      }
    }
    // initialize first column
    for (int i = 1; i < m; i++) {
      if (word1.charAt(i) == word2.charAt(0)) {
        dp[i][0] = dp[i - 1][0];
      } else {
        dp[i][0] = dp[i - 1][0] + 1;
      }
    }
    // fill the matrix
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (word1.charAt(i) == word2.charAt(j)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[m - 1][n - 1];
  }

  public static int uniquePaths(int m, int n) {
    int[] prevRow = new int[n];
    int[] currRow = new int[n];
    Arrays.fill(prevRow, 1);

    Arrays.fill(currRow, 1);
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        currRow[j] = prevRow[j] + currRow[j - 1];
      }
      prevRow = currRow;
    }
    return currRow[n - 1];
  }

  static int lengthOfLIS2(int[] nums) {
    int[] res = new int[nums.length];
    Arrays.fill(res, 1);
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          res[i] = Math.max(res[i], res[j] + 1);
        }
      }
    }
    int sol = 0;
    for (int element : res) {
      if (element > sol) {
        sol = element;
      }
    }
    return sol;
  }

  static int lengthOfLIS(int[] nums) {
    int n = nums.length;
    List<Integer> ans = new ArrayList<>();
    ans.add(nums[0]);
    for (int i = 1; i < n; i++) {
      if (nums[i] > ans.get(ans.size() - 1)) {
        ans.add(nums[i]);
      } else {
        int low = binarySearch(ans, nums[i]);
        ans.set(Math.abs(low), nums[i]);
      }
    }
    return ans.size();
  }

  private static int binarySearch(List<Integer> list, int target) {
    int low = 0;
    int high = list.size() - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      int cur = list.get(mid);
      if (target < cur) {
        high = mid - 1;
      } else if (target > cur) {
        low = mid + 1;
      } else {
        return mid; // target in list
      }
    }
    return -low; // target not in list
  }
}
