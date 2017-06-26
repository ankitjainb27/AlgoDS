package greedy;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Date 23/06/17
 *
 * @author Ankit Jain
 */
public class MeetingRoom {
    public static void main(String[] args) {
        MeetingRoom meetingRoom = new MeetingRoom();
        Interval[] intervals = {new Interval(25, 30), new Interval(5, 15), new Interval(15, 20)};
        System.out.println(meetingRoom.canAttendMeetings(intervals));
    }

    //12ms
    public boolean canAttendMeetings3(Interval[] intervals) {
        PriorityQueue<Interval> queue = new PriorityQueue<Interval>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        queue.addAll(Arrays.asList(intervals));
        for (int i = 0; i < intervals.length-1; i++) {
            Interval interval = queue.poll();
            Interval interval1 = queue.peek();
            if(interval1.start <interval.end)
                return false;
        }
        return true;
    }

    public boolean canAttendMeetings4(Interval[] intervals) {
        if(intervals == null || intervals.length==0){
            return true;
        }
        Arrays.sort(intervals, (x,y)->x.start-y.start);
        return !IntStream.range(1, intervals.length).anyMatch(i->intervals[i].start<intervals[i-1].end);
    }

    //12ms
    public boolean canAttendMeetings(Interval[] intervals) {
        Comparator<Interval> comparator = new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        };
        Arrays.sort(intervals, comparator);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end)
                return false;
        }
        return true;
    }

    //85ms
    public boolean canAttendMeetings1(Interval[] intervals) {
        Comparator<Interval> comparator = Comparator.comparingInt(o -> o.start);
        Arrays.sort(intervals, comparator);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end)
                return false;
        }
        return true;
    }

    static class Interval {
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
