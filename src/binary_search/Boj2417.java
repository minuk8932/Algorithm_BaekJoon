package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.StringTokenizer;

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

		StringTokenizer st = new StringTokenizer(NumberFormat.getInstance().format(Math.ceil(Math.sqrt(n))), ",");
		StringBuilder sb = new StringBuilder();

		while(st.hasMoreTokens()){
			sb.append(st.nextToken());
		}

		System.out.println(sb.toString());
	}
}
