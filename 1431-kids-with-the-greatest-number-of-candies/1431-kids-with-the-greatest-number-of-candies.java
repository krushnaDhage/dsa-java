class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        ArrayList<Boolean> list=new ArrayList<>();
        int[] copy = candies.clone();
        Arrays.sort(copy);
        int n=candies.length;
        int max=copy[n-1];
        for(int i=0;i<n;i++)
        {
            if((candies[i]+extraCandies)>=max)
            {
                list.add(true);
            }
            else
            {
                list.add(false);
            }
        }
       return list; 
    }
}