package hackerank;

public class ReverseList {

    public ListNode reverseList(ListNode head) {
        {
            ListNode prev = null;
            ListNode current = head;
            ListNode next = null;
            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            head = prev;
            return head;
        }

    }
}
