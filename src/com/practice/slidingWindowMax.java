class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0]; 
        int max=0;int l=0;int r=0;int j=0;
        for(int i=0;i<k;i++)
        {
            max=Math.max(nums[i],max);
        }
         int []ans = new int[nums.length-k+1];
         ans[j++]=max;
         int start=0;
        for(int i=k;i<nums.length;i++)
        {
            if (nums[start] == max) {
                max = Integer.MIN_VALUE;
                // Recompute max for current window
                for (int m = start + 1; m <= i; m++) {
                    if (nums[m] > max) {
                        max = nums[m];
                    }
                }
            } else {
                // Otherwise, just compare with new element
                max = Math.max(max, nums[i]);
            }
            ans[j++] = max;
            start++;
        
        }
    return ans;
    }
}
