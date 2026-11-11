class Solution {
    public long minimumCost(
        int cost1,
        int cost2,
        int costBoth,
        int need1,
        int need2
    ) {

        // Option 1: Buy all items separately.
        long option1 =
            (long) cost1 * need1 +
            (long) cost2 * need2;

        // Option 2: Buy BOTH items for the smaller requirement.
        // Buy the remaining individual items for the larger requirement.
        long option2;

        if (need1 <= need2) {
            option2 =
                (long) costBoth * need1 +
                (long) cost2 * (need2 - need1);
        } else {
            option2 =
                (long) costBoth * need2 +
                (long) cost1 * (need1 - need2);
        }

        // Option 3: Buy BOTH items for the larger requirement.
        long option3 =
            (long) costBoth * Math.max(need1, need2);

        return Math.min(option1,
               Math.min(option2, option3));
    }
}