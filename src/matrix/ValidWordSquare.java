package matrix;

import java.util.Arrays;
import java.util.List;

/**
 * Date 24/06/17
 *
 * @author Ankit Jain
 */
public class ValidWordSquare {
    public static void main(String[] args) {
        ValidWordSquare validWordSquare = new ValidWordSquare();
        System.out.println(validWordSquare.validWordSquare(Arrays.asList("abcd",
                "bnrt",
                "crmy",
                "dtye")));
        System.out.println(validWordSquare.validWordSquare(Arrays.asList( "abcd",
                "bnrt",
                "crm",
                "dt"
        )));
        System.out.println(validWordSquare.validWordSquare(Arrays.asList(
                "abc",
                "bdf",
                "ce"
        )));

    }

    public boolean validWordSquare(List<String> words) {
        if(words == null || words.size() == 0)
            return true;
        int n = words.size();
        for (int i = 0; i < n; i++) {
            if(words.get(i).length()>=n || words.get(i).length() <= i)
            for (int j = 0; j < words.get(i).length(); j++) {
                if(j>=n)
                    return false;
                if(words.get(j).length() <= i)
                    return false;
                if(words.get(i).charAt(j) != words.get(j).charAt(i))
                    return false;
            }
        }
        return true;
    }

}
