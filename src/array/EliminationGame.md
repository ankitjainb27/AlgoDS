#### Approach #1 Brute Force Using dynamic sized List [Time Limit Exceeded]

**Intuition**

Play the game eliminating every other number from left to right and than right to left till only one number is left.

**Algorithm**

For brute force approach we can take an dynamic sized list. We put all numbers from 1 to n in the list.
Then we start from left to right and start deleting every other number, once done we don the same from right to left.

Though seems simple, only thing we need to keep in mind is that while going from left to right when you delete a number from the list the index of all the remaining numbers decrease by one and the next number gets the same index that you are currently on. 
Due to this, when going from left to right we need to increment the number by one and not two.
When going from right to left, index of number also decreases but those are after the current number and thus already processed so we don't need to take care of anything extra. 

**Java**

```java
public class Solution {
    public int lastRemaining(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            list.add(i);
        boolean left = true;
        while (list.size() > 1) {
            if (left) {
                for (int i = 0; i < list.size(); i++)
                    list.remove(i);
            } else {
                for (int i = list.size() - 1; i >= 0; i = i - 2)
                    list.remove(i);
            }
            left = !left;
        }
        return list.get(0);
    }
}
```

**Complexity Analysis**

* Time complexity : $$O(n^2logn)$$.

For each iteration it takes $$O(n^2)$$. For each element that gets removed the remaining elements gets shifted as we are using an ArrayList and shifting take $$O(n)$$ time. Thus for each iteration it takes $$O(n^2)$$ time. 
After each iteration the size of the list gets halved and thus we multiply n^2 with log(n).
We can use Doubly Linked List to delete number in $$O(1)$$ time complexity, which will lead to time complexity of $$O(nlogn)$$.

* Space complexity : $$O(n)$$. As List has n numbers in it.

---
#### Approach #2 Brute Force Using boolean Array [Time Limit Exceeded]

**Algorithm**

To reduce the time complexity of each iteration to $$O(n)$$ we can use a boolean array. 
We keep a variable count to keep track of the count of numbers eliminated. As we are using array, in the end we increment the index by 1 to get the number that is not eliminated.
We use a boolean variable delete to delete every other number.

**Java**

```java
public class Solution {
    public int lastRemaining(int n) {
        boolean[] alive = new boolean[n];
        Arrays.fill(alive, true);
        boolean left = true;
        int count = 0;
        while (count < n-1) {
            boolean delete = true;
            if (left) {
                for (int i = 0; i < n; i++)
                    if (alive[i]) {
                        if (delete) {
                            alive[i] = false;
                            count++;
                        }
                        delete = !delete;
                    }
            } else {
                for (int i = n - 1; i >= 0; i--)
                    // Same as above for loop, can move to a function also.
                    if (alive[i]) {
                        if (delete) {
                            alive[i] = false;
                            count++;
                        }
                        delete = !delete;
                    }
            }
            left = !left;
        }
        int i;
        for (i = 0; i < n; i++) {
            if (alive[i]) break;
        }
        return i + 1;
    }
}
```

**Complexity Analysis**

* Time complexity : $$O(nlogn)$$. 
Each Iteration takes $$O(n)$$ time and as size of non-eliminated numbers reduce by half it takes $$O(logn)$$ number of iterations.

* Space complexity : $$O(n)$$. Same as the previous approach. We need $$O(n)$$ space for boolean array.
    

---
#### Approach #3 Based of Observation [Accepted]

**Intuition**

We need to observe some facts about the game. 

In each step half of the elements are eliminated and step to eliminate gets doubled (think of the number as dead but still part of the array).

First we take steps to every other element, making out initial step as 2. Then as half of the elements will get eliminated we would have to take a step of 4.
Thus, in each iteration we take steps of size double than the previous iteration.

**Algorithm**

We know two things, size gets half in each iteration and step will get doubled.
For each iteration we need to know from which number we need to start the elimination process. 
The starting element gets eliminated in two condition and thus will get updated.
1. When we go from left to right, starting number will be eliminated
2. When we go from right to left and the size is odd. 
Go from right to left and eliminate elements in 1,2,3,4 and 1,2,3,4,5. For 1,2,3,4 - 2 and 4 gets eliminated and start which 1 is saved.  For 1,2,3,4,5 - 1,3 and 5 gets eliminated and start which 1 is not saved. 

In both the above cases we need to update to the new start number. We need to update to the next number that is not eliminated after starting number, which will be the number in between starting number and next number which will be eliminated after starting number.  

Thus using the above observation we will iterate till the size is greater than 1 and stop as it becomes 1 and return start as the answer.

**Java**

```java
public class Solution {
    public int lastRemaining(int n) {
        int size = n;
        int step = 2;
        boolean left = true;
        int start = 1;
        while (size > 1) {
            if (left || size % 2 == 1)
                start = start + step / 2;
            size = size / 2;
            left = !left;
            step *= 2;
        }
        return start;
    }
}
```

**Complexity Analysis**

* Time complexity : $$O(logn)$$. 
In each iteration, the size gets halved and thus it takes $$O(logn)$$ time to reduce size to 1; 

* Space complexity : $$O(1)$$.
    