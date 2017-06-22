package contest;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ankit.ppe on 21/05/17.
 */
public class LeetcodeContest33 {
    public static void main(String[] args) {
//        int[] arr = {1, 3, 2, 2, 5, 2, 3, 7};
//        int[] arr = {1, 2, 2, 2, 2, 2, 3, 3};
        int[] arr = {2, 2, 2, 2, 2, 2, 2, 2};
//        int[] arr = {1,4,1,3,1,-14,1,-13};
//        int[] arr = {1,4,1,3,1,-14,1,-13};
        String string = "";
        LeetcodeContest33 leetcodeContest33 = new LeetcodeContest33();
        System.out.println(leetcodeContest33.fractionAddition("-7/3"));
        //        System.out.println(leetcodeContest33.findLHS(arr));
       /* int[] p1 = {0, 0};
        int[] p2 = {0, 0};
        int[] p3 = {0, 0};
        int[] p4 = {0, 0};
        System.out.println(leetcodeContest33.validSquare(p1, p2, p3, p4));
 */
    }

    class Frac {
        int num;
        int den;

        public Frac(int num, int dem) {
            this.num = num;
            this.den = dem;
        }
    }

    public String fractionAddition(String expression) {
        String[] strings = expression.split("/");
        if(strings.length <3)
            return expression;
        ArrayList<Integer> strings1 = new ArrayList<>();
        strings1.add(Integer.parseInt(strings[0]));
        for (int i = 1; i < strings.length - 1; i++) {
            if (strings[i].contains("+")) {
                String[] strings2 = strings[i].split("\\+");
                strings1.add(Integer.parseInt(strings2[0]));
                strings1.add(Integer.parseInt(strings2[1]));
            } else {
                String[] strings2 = strings[i].split("-");
                strings1.add(Integer.parseInt(strings2[0]));
                strings1.add(-1 * Integer.parseInt(strings2[1]));
            }
        }
        strings1.add(Integer.parseInt(strings[strings.length - 1]));
        Frac frac = conFrac(strings1.get(0), strings1.get(1), strings1.get(2), strings1.get(3));
        for (int i = 4; i < strings1.size(); i = i + 2) {
            frac = conFrac(frac.num, frac.den, strings1.get(i), strings1.get(i + 1));
        }
        if(frac.den <0)
            return -1*frac.num + "/" + -1*frac.den;
        return frac.num + "/" + frac.den;
    }

    private Frac conFrac(int i, int i1, int i2, int i3) {
        int den3 = gcd(i1, i3);
        den3 = (i1 * i3) / den3;
        int num3 = i * (den3 / i1) + i2 * (den3 / i3);
        return low(num3, den3);
    }

    private Frac low(int num3, int den3) {
        int cf = gcd(num3, den3);
        Frac frac =  new Frac(num3 / cf, den3 / cf);
        return frac;
    }

    private int gcd(int i1, int i3) {
        if (i1 == 0)
            return i3;
        return gcd(i3 % i1, i1);
    }

    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int l = 0;
        int r = 0;
        int maxCount = 0;
        boolean flag = false;
        while (l < nums.length && r < nums.length) {
            if (nums[l] == nums[r] || nums[r] == nums[l] + 1) {
                if (nums[r] == nums[l] + 1)
                    flag = true;
                else
                    flag = false;
                r++;
            } else {
                if (flag)
                    maxCount = Math.max(maxCount, r - l);
                l++;
            }

        }
        if (flag)
            maxCount = Math.max(maxCount, r - l);
        return maxCount;
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int d1 = findDistance(p1, p2);
        int d2 = findDistance(p1, p3);
        int d3 = findDistance(p1, p4);
        if (d1 == 0 && d2 == 0 && d3 == 0)
            return false;
        if ((d1 == d2 && d3 == 2 * d1) || (d2 == d3 && d1 == 2 * d2) || (d1 == d3 && d2 == 2 * d1)) {
            if (d3 > d1) {
                return check(p2, p3, p4);
            } else if (d1 > d3)
                return check(p3, p4, p2);
            else
                return check(p4, p2, p3);

        }
        return false;
    }

    private boolean check(int[] p2, int[] p3, int[] p4) {
        double d1 = findDistance(p2, p4);
        double d2 = findDistance(p3, p4);
        return d1 == d2;
    }

    private int findDistance(int[] p1, int[] p2) {
        return (int) (Math.pow(p2[1] - p1[1], 2) + Math.pow(p2[0] - p1[0], 2));
    }
}
