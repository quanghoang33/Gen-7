package hackerank;

public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode p1 = head.next;
        if (head.next == null) {
            return false;
        }
        ListNode p2 = head.next.next;
        while(p2 != null && p2.next != null) {
            if (p1 == p2) {
                return true;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return false;
    }

}
