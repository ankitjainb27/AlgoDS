package array;

/**
 * Date 02/08/17
 *
 * @author Ankit Jain
 * https://leetcode.com/problems/teemo-attacking/description/
 */
public class TeemoAttacking {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int totalTime = 0;
        int lastEffectTime = 0;
        for (int time : timeSeries) {
            totalTime += (time < lastEffectTime ? time - lastEffectTime : 0) + duration;
            lastEffectTime = time + duration;
        }
        return totalTime;
    }
}
