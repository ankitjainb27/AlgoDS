package maths;

import java.util.LinkedList;

/**
 * Date 23/06/17
 *
 * @author Ankit Jain
 *         https://leetcode.com/problems/moving-average-from-data-stream/#/description
 */
public class MovingAverage {

    int size;
    int count = 0;
    LinkedList<Integer> list;
    double lastAvg;

    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
        this.size = size;
        list = new LinkedList<>();
    }

    public double next(int val) {
        if (size == 0)
            return 0.0;
        if (count < size)
            lastAvg = (lastAvg * count + val) / ((double) (++count));
        else
            lastAvg += (val - list.remove(0)) / (double)(size);
        list.add(val);
        return lastAvg;
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1));
        System.out.println(movingAverage.next(10));
        System.out.println(movingAverage.next(3));
        System.out.println(movingAverage.next(5));
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
