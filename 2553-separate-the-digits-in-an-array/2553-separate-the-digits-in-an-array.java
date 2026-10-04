class Solution {
    public int[] separateDigits(int[] nums) {
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<nums.length;i++)
        {
           String str=String.valueOf(nums[i]);
           for(int j=0;j<str.length();j++)
           {
            list.add(str.charAt(j)-'0');
           }
        }
        int[] result = new int[list.size()];
         for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}