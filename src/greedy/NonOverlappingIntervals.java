package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Date 08/08/17
 *
 * @author Ankit Jain
 */
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval o1, Interval o2)
            {
                return o1.end != o2.end?o1.end-o2.end:o1.start-o2.start;
            }
        });
        int count = 0;
        if(intervals.length == 0) return count;
        Interval prev = intervals[0];
        for(int i = 1; i<intervals.length;i++)
        {
            if(intervals[i].start<prev.end)
                count++;
            else prev = intervals[i];
        }
        return count;
    }

    public int eraseOverlapIntervals1(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        int count = 0;
        if (intervals.length == 0) return count;
        Interval prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < prev.end) {
                count++;
                if (prev.end > intervals[i].end)
                    prev = intervals[i];
            } else prev = intervals[i];
        }
        return count;
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
