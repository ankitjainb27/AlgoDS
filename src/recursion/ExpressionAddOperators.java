package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 22/08/17 at 9:38 AM
 *
 * @author Ankit Jain
 */
public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> list = new ArrayList<>();
        aoUtil(num, list, target, "", 1);
        return list;
    }

    private void aoUtil(String chars, List<String> list, int target, String st, int index) {
        if (target == 0) {
            if (st.length() > 0)
                list.add(st);
            return;
        }
        for (int i = index; i < chars.length(); i++) {

        }
    }
}
