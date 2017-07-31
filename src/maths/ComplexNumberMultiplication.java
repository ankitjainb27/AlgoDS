package maths;

/**
 * Date 28/07/17
 * @author Ankit Jain
 * https://leetcode.com/problems/complex-number-multiplication/tabs/description
 */
public class ComplexNumberMultiplication {
    public static void main(String[] args) {
        System.out.println(new ComplexNumberMultiplication().complexNumberMultiply("1+1i", "1+-2i"));
    }

    public String complexNumberMultiply(String a, String b) {
        int i = a.indexOf("+");
        int j = b.indexOf("+");
        int[] a1 = new int[2];
        int[] b1 = new int[2];
        a1[0] = Integer.valueOf(a.substring(0,i));
        a1[1] = Integer.valueOf(a.substring(i+1,a.length()-1));
        b1[0] = Integer.valueOf(b.substring(0,j));
        b1[1] = Integer.valueOf(b.substring(j+1,b.length()-1));
        return (a1[0]*b1[0] - a1[1]*b1[1]) + "+" + (a1[0]*b1[1] + a1[1]*b1[0])+ "i";
    }
}
