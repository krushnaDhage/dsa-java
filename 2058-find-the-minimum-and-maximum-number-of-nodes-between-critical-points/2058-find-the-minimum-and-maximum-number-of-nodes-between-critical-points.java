class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        ListNode prev = head;
        ListNode curr = head.next;

        int pos = 1;

        int first = -1;
        int last = -1;

        int minDist = Integer.MAX_VALUE;

        while (curr.next != null) {

            ListNode next = curr.next;

            // Check whether curr is a critical point
            if ((curr.val > prev.val && curr.val > next.val) ||
                (curr.val < prev.val && curr.val < next.val)) {

                if (first == -1) {
                    // First critical point
                    first = pos;
                } else {
                    // Distance from previous critical point
                    minDist = Math.min(minDist, pos - last);
                }

                last = pos;
            }

            prev = curr;
            curr = next;
            pos++;
        }

        // Fewer than two critical points
        if (first == -1 || first == last) {
            return new int[]{-1, -1};
        }

        int maxDist = last - first;

        return new int[]{minDist, maxDist};
    }
}