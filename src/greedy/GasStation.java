package greedy;

/**
 * Created on 19/08/17 at 9:39 PM
 *
 * @author Ankit Jain
 */
public class GasStation {
    int canCompleteCircuit2(int[] gas, int[] cost) {

        int start = gas.length - 1;
        int end = 0;
        int sum = gas[start] - cost[start];
        while (start > end) {
            if (sum >= 0) {
                sum += gas[end] - cost[end];
                ++end;
            } else {
                --start;
                sum += gas[start] - cost[start];
            }
        }
        return sum >= 0 ? start : -1;
    }

    int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0, total = 0, tank = 0;
        //if car fails at 'start', record the next station
        for (int i = 0; i < gas.length; i++)
            if ((tank = tank + gas[i] - cost[i]) < 0) {
                start = i + 1;
                total += tank;
                tank = 0;
            }
        return (total + tank < 0) ? -1 : start;
    }

    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int rem = 0;
        int start = 0;
        int n = gas.length;
        for (int i = 0; i < 2 * n; i++) {
            int j = i % n;
            rem += gas[j] - cost[j];
            if (rem >= 0 && start == (i + 1) % n) return start;
            if (rem < 0) {
                start = (i + 1) % n;
                rem = 0;
            }

        }
        return -1;
    }
}
