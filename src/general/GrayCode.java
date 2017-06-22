package general;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 05/06/17
 *
 * @author Ankit Jain
 */
public class GrayCode {
    public static void main(String[] args) {
        GrayCode grayCode = new GrayCode();
        System.out.println(grayCode.grayCode(3));
    }

    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        if(n == 0)
            return list;
        if(n == 1)
            return list;
        list.add(1);
        for (int i = 1; i < n; i++) {
            addToList(list, (int)Math.pow(2,i));
        }
        return list;
    }

    private void addToList(List<Integer> list, int pow) {
        int len = list.size();
        for (int i = len-1; i >=0 ; i--) {
            list.add(pow+list.get(i));
        }
    }
}
