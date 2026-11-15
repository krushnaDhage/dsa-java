class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n=nums.length;
        int totSum=0;
        int[] ans=new int[n];
        for(int i=0;i<n;i++)
        {
            totSum=totSum+nums[i];
        }
        int leftSum=0;
        int rightSum=0;
        for(int i=0;i<n;i++)
        {
            rightSum=totSum-leftSum-nums[i];
            ans[i]=Math.abs(rightSum-leftSum);
            leftSum=leftSum+nums[i];
        }
        return ans;
    }
}