package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 2417번: 정수 제곱근
 *
 *	@see https://www.acmicpc.net/problem/2417/
 *
 */
public class Boj2417 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());

		System.out.println(binarySearch(n));
	}

	private static long binarySearch(long target) {
		long root = (long) Math.sqrt(target);
		long pow = root * root;

		return root + (pow >= target ? 0: 1);
	}
}
