package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * 
 * 	@author minchoba
 *	백준 2338번: 긴자리 계산
 *
 *	@see https://www.acmicpc.net/problem/2338/
 *
 */
public class Boj2338 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger big1 = new BigInteger(br.readLine());
		BigInteger big2 = new BigInteger(br.readLine());
		
		System.out.println(big1.add(big2));
		System.out.println(big1.subtract(big2));
		System.out.println(big1.multiply(big2));
	}
}
