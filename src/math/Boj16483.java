package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16483번: 접시안의 원
 *
 *	@see https://www.acmicpc.net/problem/16483/
 *
 */
public class Boj16483 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double T = Math.pow(Double.parseDouble(br.readLine()) / 2, 2);
		System.out.println(Math.round(T));
	}
}
