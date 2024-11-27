package linkedListTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import linkedList.ListNode;
import linkedList.Solution;

public class SolutionTest {
  
  ListNode five = new ListNode(50);
  ListNode four = new ListNode(4);
  ListNode three = new ListNode(3, four);
  //ListNode two = new ListNode(20, three);
  ListNode one = new ListNode(1, three);
  ListNode four1 = new ListNode(4);
  ListNode two1 = new ListNode(2, four1);
  ListNode one1 = new ListNode(1, two1);
  
  Solution sol = new Solution();
  
  @Test
  public void test2() {
    ListNode four = new ListNode(4);
    ListNode three = new ListNode(3, four);
    ListNode one = new ListNode(1, three);
    ListNode four1 = new ListNode(4);
    ListNode two1 = new ListNode(2, four1);
    ListNode one1 = new ListNode(1, two1);
    ListNode res = sol.mergeTwoListsIterative(one1, one);
  }
  
  //@Test
  public void test1() {
    ListNode four = new ListNode(4);
    ListNode three = new ListNode(3, four);
    ListNode one = new ListNode(1, three);
    
    ListNode four1 = new ListNode(4);
    ListNode two1 = new ListNode(2, four1);
    ListNode one1 = new ListNode(1, two1);
    
    ListNode res = sol.mergeTwoLists(one1, one);
//    while (res != null) {
//      System.out.println(res.getVal());
//      res = res.next;
//    }
  }
  
  public void test() {
    
    assertEquals(five, sol.reverseList(one));
    //assertEquals(five, sol.reverseList(four));
  }

}
