/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} head
 * @return {boolean}
 */
var hasCycle = function(head) {
    let slow = head;
    let quick = head;

    while (quick != null && quick.next != null) {
        slow = slow.next;
        quick = quick.next.next;

        if (slow === quick) {
            return true
        }
    }

    return false;
};
