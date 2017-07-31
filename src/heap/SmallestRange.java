package heap;

import java.util.*;

/**
 * Date 27/07/17
 *
 * @author Ankit Jain
 */
public class SmallestRange {
    public static void main(String[] args) {

        SmallestRange smallestRange = new SmallestRange();

        //[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
        int[][] vals = {{4, 10, 15, 24, 26}, {0, 9, 12, 20}, {5, 18, 22, 30}};
        List list = new ArrayList();
        for (int i = 0; i < vals.length; i++) {
            List<Integer> values = new ArrayList<>();
            for (int j = 0; j < vals[i].length; j++) {
                values.add(vals[i][j]);
            }
            list.add(values);
        }
//        System.out.println(list);
        System.out.println(Arrays.toString(smallestRange.smallestRange1(list)));
    }

    //Leads to time limit exceeded
    public int[] smallestRange(List<List<Integer>> nums) {
        int[] result = new int[2];
        List<Cell> list = new LinkedList<>();
        int range = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            Cell cell = new Cell(i, 0);
            list.add(findWhereToInsert(list, cell, nums), cell);
        }
        Cell minCell;
        Cell maxCell;
        while (true) {
            minCell = list.get(0);
            maxCell = list.get(list.size() - 1);
            int min = nums.get(minCell.i).get(minCell.j);
            int max = nums.get(maxCell.i).get(maxCell.j);
            int newRange = max - min;
            if (range > newRange) {
                range = newRange;
                result[0] = min;
                result[1] = max;
            }
            list.remove(0);
            minCell.j = minCell.j + 1; //Check if we can use increment operator here.
            if (minCell.j == nums.get(minCell.i).size()) break;
            list.add(findWhereToInsert(list, minCell, nums), minCell);
        }
        return result;
    }

    private int findWhereToInsert(List<Cell> list, Cell minCell, List<List<Integer>> nums) {
        int i;
        for (i = 0; i < list.size(); i++) {
            Cell cell = list.get(i);
            if (nums.get(cell.i).get(cell.j) >= nums.get(minCell.i).get(minCell.j)) {
                return i;
            }
        }
        return i;
    }

    //Using Priority Queue, it doesn't lead to TLE
    public int[] smallestRange1(List<List<Integer>> nums) {
        int[] result = new int[2];
        PriorityQueue<Cell> min = new PriorityQueue<Cell>(new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return nums.get(o1.i).get(o1.j) - nums.get(o2.i).get(o2.j);
            }
        });

        Cell maxCell = null;
        int range = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            Cell cell = new Cell(i, 0);
            min.add(cell);
            if (maxCell == null || nums.get(maxCell.i).get(maxCell.j) <= nums.get(cell.i).get(cell.j))
                maxCell = cell;
        }
        Cell minCell;
        while (true) {
            minCell = min.poll();
            int newRange = nums.get(maxCell.i).get(maxCell.j) - nums.get(minCell.i).get(minCell.j);
            if (range > newRange) {
                range = newRange;
                result[0] = nums.get(minCell.i).get(minCell.j);
                result[1] = nums.get(maxCell.i).get(maxCell.j);
            }
            minCell.j = minCell.j + 1; //Check if we can use increment operator here.
            if (minCell.j == nums.get(minCell.i).size()) break;
            min.add(minCell);
            if (nums.get(maxCell.i).get(maxCell.j) <= nums.get(minCell.i).get(minCell.j))
                maxCell = minCell;
        }
        return result;
    }

    class Cell {
        //        int val;
        int i;
        int j;

        public Cell(int i, int j) {
            this.i = i;
            this.j = j;
//            this.val = val;
        }
    }

}
