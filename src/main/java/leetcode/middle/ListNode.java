package leetcode.middle;

public class ListNode {
      public int val;
      public ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

      @Override
      public String toString() {
            StringBuilder sb = new StringBuilder("[" + val);
            ListNode temp = next;
            while (temp != null) {
                  sb.append(", ").append(temp.val);
                  temp = temp.next;
            }
            return sb.append("]").toString();
      }
}
