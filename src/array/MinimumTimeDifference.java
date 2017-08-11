package array;

import java.util.*;

/**
 * Date 02/08/17
 *
 * @author Ankit Jain
 */
public class MinimumTimeDifference {
    public static void main(String[] args) {
        MinimumTimeDifference minimumTimeDifference = new MinimumTimeDifference();
        List<String> list = new ArrayList<>();
        list.add("23:59");
        list.add("00:00");
        list.add("05:30");
        System.out.println(minimumTimeDifference.findMinDifference3(list));
    }

    //Fastest
    public int findMinDifference(List<String> timePoints) {
        boolean[] mark = new boolean[24 * 60];
        for (String time : timePoints) {
            String[] t = time.split(":");
            int h = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            if (mark[h * 60 + m]) return 0;
            mark[h * 60 + m] = true;
        }

        int prev = 0, min = Integer.MAX_VALUE;
        boolean flag = false;
        int first = Integer.MAX_VALUE;
        for (int i = 0; i < 24 * 60; i++) {
            if (mark[i]) {
                if (flag) {
                    min = Math.min(min, i - prev);
                }
                if (!flag) {
                    flag = true;
                    first = i;
                }
                prev = i;
            }
        }

        min = Math.min(min, (24 * 60 - prev + first));

        return min;
    }

    //Notice sorting string lexicographically, does the trick
    public int findMinDifference1(List<String> timePoints) {
        Collections.sort(timePoints, new Comparator<String>() {
            public int compare(String time1, String time2) {
                return time1.compareTo(time2);
            }
        });
        String diff = "24:00";
        String time2 = timePoints.get(0);
        for (int i = 1; i < timePoints.size(); i++) {
            String diff1 = find(timePoints.get(i), timePoints.get(i - 1));
            diff = diff.compareTo(diff1) < 0 ? diff : diff1;
        }
        String lastTime = (Integer.parseInt(time2.substring(0, 2)) + 24) + ":" + time2.substring(3, 5);
        String diff1 = find(lastTime, timePoints.get(timePoints.size() - 1));
        diff = diff.compareTo(diff1) < 0 ? diff : diff1;
        return Integer.valueOf(diff.substring(0, 2)) * 60 + Integer.valueOf(diff.substring(3, diff.length()));
    }

    private String find(String s1, String s2) {
        int min = Integer.parseInt(s1.substring(3, 5)) - Integer.parseInt(s2.substring(3, 5));
        int hour = Integer.parseInt(s1.substring(0, 2)) - Integer.parseInt(s2.substring(0, 2));
        if (min < 0) {
            hour--;
            min += 60;
        }
        String hourString = hour < 10 ? "0" + hour : "" + hour;
        String minString = min < 10 ? "0" + min : "" + min;
        return hourString + ":" + minString;

    }

    public int findMinDifference3(List<String> timePoints) {
        int n = timePoints.size();
        List<Time> times = new ArrayList<>();
        for (String tp : timePoints) {
            String[] strs = tp.split(":");
            times.add(new Time(Integer.parseInt(strs[0]), Integer.parseInt(strs[1])));
        }
        Collections.sort(times);
        Time earlist = times.get(0);
        times.add(new Time(earlist.h + 24, earlist.m));
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int diff = (int) Math.abs(times.get(i).getDiff(times.get(i + 1)));
            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }

}

class Time implements Comparable<Time> {
    int h;
    int m;

    public Time(int h, int m) {
        this.h = h;
        this.m = m;
    }

    public int compareTo(Time other) {
        if (this.h == other.h) {
            return this.m - other.m;
        }
        return this.h - other.h;
    }

    public int getDiff(Time other) {
        return (this.h - other.h) * 60 + (this.m - other.m);
    }
}
