package array;

/**
 * Created by ankit.ppe on 02/04/17.
 */
public class Split {
    public static void main(String[] args) {
        Split split = new Split();
        int[] a  = {1, 0, 2, 0, 1, 2, 3, 1, 1, 1, 3, 3, 3, 1, 0, 3, 0, 3, 1, 3, -1, 2, 2, 1, 1, 3, 1};
        System.out.println(split.splitArray(a));
    }

    public boolean splitArray(int[] nums) {
        int sum = 0;
        int n = nums.length;
        if(n < 6)
            return false;
        for(int i  = 0 ;i< n ;i++)
            sum+=nums[i];
        int i = 1;
        int sumi = nums[0];
        int k = n-2;
        int sumk = nums[n-1];
        while(k-i>2)
        {
            if(sumi == sumk)
            {
                int fsum = sum-(sumk+sumi+nums[i] + nums[k]);
                int sumj = nums[i+1];
                for(int j = i+2;j<k-1;j++)
                {
                    if(sumj == sumi)
                    {
                        int rsum = fsum - sumj - nums[j];
                        if(sumj == rsum)
                            return true;
                    }
                    sumj+=nums[j];
                }
                sumi+=nums[i];
                i++;
                sumk+=nums[k];
                k--;
            }
            else if(sumi < sumk)
            {
                if(nums[k] > 0)
                    sumi+=nums[i++];
                else
                    sumk+=nums[k--];
            }
            else
            {
                if(nums[i] > 0)
                {
                    sumk+=nums[k];
                    k--;
                }
                else
                {
                    sumi+=nums[i];
                    i++;
                }
            }
        }
        return false;
    }
}
