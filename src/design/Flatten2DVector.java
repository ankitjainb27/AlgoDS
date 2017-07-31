package design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Date 01/07/17
 *
 * @author Ankit Jain
 */
public class Flatten2DVector {

    //My Solution
    public class Vector2D implements Iterator<Integer> {

        List<Iterator> list;

        public Vector2D(List<List<Integer>> vec2d) {
            list = new LinkedList<>();
            for (List<Integer> l : vec2d)
                if (l.size() > 0)
                    list.add(l.iterator());
        }

        @Override
        public Integer next() {
            Iterator it = list.get(0);
            Integer i = (Integer) it.next();
            if (!it.hasNext()) list.remove(0);
            return i;
        }

        @Override
        public boolean hasNext() {
            return !list.isEmpty();
        }
    }

}
