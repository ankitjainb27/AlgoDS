package bit;

/**
 * Date 01/08/17
 *
 * @author Ankit Jain
 */
public class HammingDistance {
    public static void main(String[] args) {
        HammingDistance hammingDistance = new HammingDistance();
        System.out.println(hammingDistance.hammingDistance(1,4));
        System.out.println(hammingDistance.hammingDistance1(1,4));
    }

    //Using Integer class
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x^y);
    }

    //Using Brain kernighan's Algo
    public int hammingDistance1(int x, int y) {
        int xor = (x^y);
        int count = 0;
        while(xor!= 0)
        {
            xor = (xor&(xor-1));
            count++;
        }
        return count;
    }
}
