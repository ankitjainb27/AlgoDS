package design;

import java.util.List;

/**
 * Date 01/07/17
 *
 * @author Ankit Jain
 * https://leetcode.com/problems/zigzag-iterator/#/description
 */

//My Solution
public class ZigzagIterator {
    int i1 = 0;
    int i2 = 0;
    List<Integer> v1, v2;
    int len1;
    int len2;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
        len1 = v1.size();
        len2 = v2.size();
    }

    public int next() {
        if (i1 < len1  || i2 < len2) {
            if ((i1 < len1  &&  i2 < len2 && i1 < i2) || i2==len2)
                return v1.get(i1++);
            else
                return v2.get(i2++);
        }
        return -1;
    }

    public boolean hasNext() {
        return (i1 < len1 || i2 < len2) ? true : false;
    }
}
