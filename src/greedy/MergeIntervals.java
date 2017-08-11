package greedy;

import java.util.*;

/**
 * Date 07/08/17
 *
 * @author Ankit Jain
 */
public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        List<Interval> result = new ArrayList<>();
        int startValue = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || start[i + 1] > end[i]) {
                result.add(new Interval(start[startValue], end[i]));
                startValue = i + 1;
            }
        }
        return result;
    }


    public List<Interval> merge2(List<Interval> intervals) {
        if (intervals.size() <= 1)
            return intervals;

        // Sort by ascending starting point using an anonymous Comparator
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

        List<Interval> result = new LinkedList<Interval>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval : intervals) {
            if (interval.start <= end) // Overlapping intervals, move the end if needed
                end = Math.max(end, interval.end);
            else {                     // Disjoint intervals, add the previous one and reset bounds
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }

        // Add the last interval
        result.add(new Interval(start, end));
        return result;
    }

    public List<Interval> merge1(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        List<Interval> result = new ArrayList<>();
        if (intervals.size() == 0) return result;
        result.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            Interval next = intervals.get(i);
            Interval prev = result.get(result.size() - 1);
            if (next.start <= prev.end)
                prev.end = Math.max(prev.end, next.end);
            else
                result.add(next);
        }
        return result;
    }

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
