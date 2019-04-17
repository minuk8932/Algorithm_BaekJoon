package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * 
 * 	@author minchoba
 *	백준 13706번: 제곱근
 *
 *	@see https://www.acmicpc.net/problem/13706/
 *
 */
public class Boj13706 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		
		System.out.println(num.length() < 18 ? (long) Math.sqrt(Long.parseLong(num)) : binarySearch(num));
	}
	
	private static String binarySearch(String str) {		// 매우 큰수는 이분 탐색으로
		BigInteger one = BigInteger.ONE;
        BigInteger two = new BigInteger("2");

        BigInteger target = new BigInteger(str);

        BigInteger left = BigInteger.ONE;
        BigInteger right = new BigInteger(str);

        BigInteger mid = null, x;

        while (left.compareTo(right) <= 0) {

            mid = (left.add(right)).divide(two);
            x = mid.multiply(mid);

            int comp = x.compareTo(target);
             
            if (comp == 1) right = mid.subtract(one);
            else if (comp == -1) left = mid.add(one);
            else break;
        }
		
		return mid.toString();
	}
}
