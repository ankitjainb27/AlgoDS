package design;

import java.util.*;

/**
 * Date 01/07/17
 *
 * @author Ankit Jain
 */
public class SnakeGame {

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame(2, 2, new int[][]{{1, 1}, {0, 0}});
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("L"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("R"));
//        System.out.println(snakeGame.move("U"));
    }

    /**
     * Initialize your data structure here.
     *
     * @param width - screen width
     * @param height - screen height
     * @param food - A list of food positions
     * E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
     */
    LinkedList<int[]> queue;
    int width;
    int height;
    LinkedList<int[]> food;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        queue = new LinkedList<>();
        int[] val = {0, 0};
        queue.add(val);
        this.food = new LinkedList<>(Arrays.asList(food));
    }

    /**
     * Moves the snake.
     *
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over.
     * Game over when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        int[] prev = queue.pollFirst();
        int[] curr;
        if(!queue.isEmpty())
            curr = Arrays.copyOf(queue.peekLast(),2);
        else
            curr = Arrays.copyOf(prev,2);
        switch (direction) {
            case "U":
                curr[0] = curr[0] - 1;
                break;
            case "L":
                curr[1] = curr[1] - 1;
                break;
            case "R":
                curr[1] = curr[1] + 1;
                break;
            case "D":
                curr[0] = curr[0] + 1;
                break;
        }

        if (curr[0] < 0 || curr[0] >= height || curr[1] < 0 || curr[1] >= width) return -1;
        for (int i = 0; i < queue.size()-1; i++) {
            if (Arrays.equals(queue.get(i), curr))
                return -1;
        }
        int[] currFood = food.peek();
        if (Arrays.equals(curr, currFood)) {
            food.poll();
            queue.addFirst(prev);
        }
        queue.add(curr);
        return queue.size()-1;
    }
}
