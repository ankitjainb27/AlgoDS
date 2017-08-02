package string;

/**
 * Created by ankit.ppe on 23/04/17.
 */
public class ClosestPalindrome {

    public static void main(String[] args) {
        System.out.println((int)' ');
        ClosestPalindrome closestPalindrome =  new ClosestPalindrome();
        System.out.println(closestPalindrome.nearestPalindromic("11"));
    }

    public String nearestPalindromic(String n) {
        boolean flag = false;
        for (int i = 0; i < n.length()/2; i++) {
            if(n.charAt(i) != n.charAt(n.length()-1-i))
            {
                flag = true;
                n = changeCharInPosition(n.length()-1-i,n.charAt(i), n);
            }
        }
        if(!flag)
        {
            if(n.length()%2 == 0)
            {
                Integer integer = Integer.parseInt(String.valueOf(n.charAt(n.length()/2)))-1;
                n = changeCharInPosition(n.length()/2-1, String.valueOf(integer).charAt(0), n);
                n = changeCharInPosition(n.length()/2, String.valueOf(integer).charAt(0), n);
            }
            else {
                Integer integer = Integer.parseInt(String.valueOf(n.charAt(n.length()/2)))-1;
                n = changeCharInPosition(n.length()/2, String.valueOf(integer).charAt(0), n);
            }
        }
        return n;
    }

    public String changeCharInPosition(int position, char ch, String str){
        char[] charArray = str.toCharArray();
        charArray[position] = ch;
        return new String(charArray);
    }

}
