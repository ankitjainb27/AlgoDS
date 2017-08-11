package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Date 04/08/17
 *
 * @author Ankit Jain
 */
public class DifferentWaysAddParentheses {
    public static void main(String[] args) {
        DifferentWaysAddParentheses differentWaysAddParentheses = new DifferentWaysAddParentheses();
        System.out.println(differentWaysAddParentheses.diffWaysToCompute("2-1-1-1-1"));
    }

    public List<Integer> diffWaysToCompute(String input) {
        String[] operand1 = input.split("[+]|[-]|[*]");
        String[] operator = input.substring(operand1[0].length(), input.length()).split("\\d+");
        int[] operand = new int[operand1.length];
        for (int i = 0; i < operand.length; i++) {
            operand[i] = Integer.parseInt(operand1[i]);
        }
        List<Integer> result = new ArrayList<>();

        List<Integer>[][] lists = new ArrayList[operand.length][operand.length];
        diffWaysToComputeUtil(operand, operator, result, 0, operand.length - 1, lists);
        return result;
    }

    private void diffWaysToComputeUtil(int[] operand, String[] operator, List<Integer> result, int l, int r, List<Integer>[][] lists) {
        if (lists[l][r] != null) {
            result.addAll(lists[l][r]);
            return;
        }
        if (r == l) {
            result.add(operand[l]);
            return;
        }
        if (r - l == 1) {
            result.add(operate(operand[l], operand[r], operator[l]));
            return;
        }
        for (int i = l; i < r; i++) {
            List<Integer> res2 = new ArrayList<>();
            diffWaysToComputeUtil(operand, operator, res2, l, i, lists);
            List<Integer> res = new ArrayList<>();
            diffWaysToComputeUtil(operand, operator, res, i + 1, r, lists);
            for (int j = 0; j < res.size(); j++) {
                for (int k = 0; k < res2.size(); k++) {
                    result.add(operate(res2.get(k), res.get(j), operator[i]));
                }
            }
            lists[l][r] = result;
        }
    }

    private int operate(Integer integer, Integer integer1, String character) {
        switch (character) {
            case "+":
                return integer + integer1;
            case "-":
                return integer - integer1;
            case "*":
                return integer * integer1;
        }
        return 0;
    }


    private int operate(Integer integer, Integer integer1, Character character) {
        switch (character) {
            case '+':
                return integer + integer1;
            case '-':
                return integer - integer1;
            case '*':
                return integer * integer1;
        }
        return 0;
    }

    public List<Integer> diffWaysToCompute1(String input) {
        List<Integer> operand = new ArrayList<>();
        List<Character> operator = new ArrayList<>();
        int prev = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '*' || ch == '-' || ch == '+') {
                operator.add(ch);
                operand.add(Integer.parseInt(input.substring(prev, i)));
                prev = i + 1;
            }
        }
        operand.add(Integer.parseInt(input.substring(prev, input.length())));
        List<Integer> result = new ArrayList<>();
        HashMap<String, List<Integer>> map = new HashMap<>();
        diffWaysToComputeUtil(operand, operator, result, 0, operand.size() - 1, map);
        return result;
    }

    private void diffWaysToComputeUtil(List<Integer> operand, List<Character> operator, List<Integer> result, int l, int r, HashMap<String, List<Integer>> map) {
        if (map.containsKey(l + "-" + r)) {
            result.addAll(map.get(l + "-" + r));
            return;
        }
        if (r == l) {
            result.add(operand.get(l));
            return;
        }
        if (r - l == 1) {
            result.add(operate(operand.get(l), operand.get(r), operator.get(l)));
            return;
        }
        for (int i = l; i < r; i++) {
            List<Integer> res2 = new ArrayList<>();
            diffWaysToComputeUtil(operand, operator, res2, l, i, map);
            List<Integer> res = new ArrayList<>();
            diffWaysToComputeUtil(operand, operator, res, i + 1, r, map);
            for (int j = 0; j < res.size(); j++) {
                for (int k = 0; k < res2.size(); k++) {
                    result.add(operate(res2.get(k), res.get(j), operator.get(i)));
                }
            }
            map.put(l + "-" + r, result);
        }
    }

}
