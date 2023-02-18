package hackerank;

public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        Map<ListNode, Boolean> mark = new HashMap<>();
        ListNode current = head;
        while ( current != null) {
            if (mark.get(current) != null && mark.get(current)) {
                return true;
            }
            mark.put(current, true);
            current = current.next;
        }
        return false;
    }
}
