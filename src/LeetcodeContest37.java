import java.lang.reflect.Array;
import java.util.*;

/**
 * Date 18/06/17
 *
 * @author Ankit Jain
 */
public class LeetcodeContest37 {
    public static void main(String[] args) {
        LeetcodeContest37 contest37 = new LeetcodeContest37();
        int[][] mat =
                {{1, 2, 3},
                        {4, 5},
                        {1, 2, 3}};
        int[][] mat1 =
                {{1, 5},
                        {3, 4}};
        HashSet<String> set   = new HashSet<>();
        List<String> list = new ArrayList<>();
        list.add("Ankit");
        list.add("Doing");
        set.addAll(list);
        System.out.println(set);
//        System.out.println(contest37.maxDistance(mat1));
//        System.out.println(contest37.smallestFactorization(18000000));

        String[] temp = {"abc","bcd","acef","xyz","az","ba","a","z"};
//        System.out.println(contest37.groupStrings(temp));
        int[] a = {12,4,12,54,23,7865,234};
        Integer[] b = {12,4,12,54,23,7865,234};
        Arrays.sort(a);
        List list1 = Arrays.asList(b);
        Collections.reverse(list1);
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
        System.out.println(Arrays.toString(a));

    }



    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strings) {
            int offset = str.charAt(0) - 'a';
            String key = "";
            for (int i = 0; i < str.length(); i++) {
                char c = (char) (str.charAt(i) - offset);
                if (c < 'a') {
                    c += 26;
                }
                key += c;
            }
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<String>();
                map.put(key, list);
            }
            map.get(key).add(str);
        }
        result.addAll(map.values());
        /*for (String key : map.keySet()) {
            List<String> list = map.get(key);
            Collections.sort(list);
            result.add(list);
        }*/
        return result;
    }

    public int smallestFactorization(int a) {
        if(a<10)
            return a;
        List<Integer> list = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            list.add(0);
        }
        for (int i = 9; i >=2 ; i--) {
            int count = 0;
            while (a%i == 0)
            {
                count++;
                a = a/i;
            }
                list.set(i,count);
        }
        System.out.println(a);
        if(a > 1)
            return 0;
        System.out.println(list);
        int val = 0;
        for (int i = 0; i<list.size() ; i++) {
            int b = list.get(i);
            if(b != 0)
            {
                for (int j = 0; j < b; j++) {
                    if(val > (float)(Integer.MAX_VALUE/10)-i)
                        return 0;
                    val = val*10+i;
                }
            }
        }
        /*for (int i = list.size()-1; i>=0 ; i--) {
            int b = list.get(i);
            if(b != 0)
            {
                for (int j = 0; j < b; j++) {
                    val = val*10+b;
                }
            }
        }*/
        return val;
    }


    public int maxDistance(int[][] arrays) {
        Ele min = new Ele(arrays[0][0],0);
        Ele min2 = new Ele(Integer.MAX_VALUE,0);
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i][0] <= min.val)
            {
                min2.val = min.val;
                min2.index = min.index;
                min.val = arrays[i][0];
                min.index = i;
            }
            else if (arrays[i][0] <= min2.val)
            {
                min2.val = arrays[i][0];
                min2.index = i;
            }
        }
        System.out.println(min.toString());
        System.out.println(min2.toString());
        Ele max = new Ele(arrays[0][arrays[0].length-1],0);
        Ele max2 = new Ele(Integer.MIN_VALUE,0);
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i][arrays[i].length-1] >= max.val)
            {
                max2.val = max.val;
                max2.index = max.index;
                max.val = arrays[i][arrays[i].length-1];
                max.index = i;
            }
            else if (arrays[i][arrays[i].length-1] >= max2.val)
            {
                max2.val = arrays[i][arrays[i].length-1];
                max2.index = i;
            }
        }
        System.out.println(max.toString());
        System.out.println(max2.toString());

        if(min.index != max.index)
            return Math.abs(max.val - min.val);
        int val1 = Math.abs(max.val - min2.val);
        int val2 = Math.abs(max2.val - min.val);
        return Math.max(val1,val2);
    }

    class Ele {
        public int val;
        public int index;

        public Ele(int val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public String toString() {
            return val + "--" + index;
        }
    }
}
