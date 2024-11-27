package linkedList;

public class Solution {
  
  public boolean isPalindrome(ListNode head) {
    // find the middle one
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    // the middle one is slow

    // change the latter half's next to its previous
    ListNode prev = slow, temp;
    slow = slow.next;
    prev.next = null;
    while (slow != null) {
      temp = slow.next;
      slow.next = prev;
      prev = slow;
      slow = temp;
    }
    // prev is the last node

    // use two pointers, traverse the list from the start and the end
    fast = head;
    slow = prev;
    while (slow != null) {
      if (fast.val != slow.val)
        return false;
      fast = fast.next;
      slow = slow.next;
    }
    return true;
  }
  
  public ListNode mergeTwoListsIterative(ListNode list1, ListNode list2) {
    ListNode preHead = new ListNode(0);
    ListNode last = preHead;
    
    while(list1 != null && list2 != null) {
        if(list1.val > list2.val) {
            last.next = list2;
            list2 = list2.next;
        } else {
            last.next = list1;
            list1 = list1.next;
        }
        last = last.next;
    }
    
    if(list1 == null) {
        last.next = list2;
    } else {
        last.next = list1;
    }
    
    return preHead.next;

  }
  
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null)
      return list2;
    if (list2 == null)
      return list1;
    ListNode node1;

    if (list1.val < list2.val) {
      node1 = list1.next;
      list1.next = list2;
      mergeTwoLists(node1, list2);
      return list1;
    } else {
      node1 = list2.next;
      list2.next = list1;
      mergeTwoLists(list1, node1);
      return list2;
    }

  }
  
  public ListNode reverseList(ListNode head) {
    if (head.next == null) return head;
    ListNode node1 = head.next;

    while (node1 != null) {
        
        ListNode node2 = node1.next;

        node1.next = head;

        head = node1;

        node1 = node2;
    }
    return head;
}

}

