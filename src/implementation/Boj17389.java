package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17389번: 보너스 점수
 *
 *	@see https://www.acmicpc.net/problem/17389/
 *
 */
public class Boj17389 {
	private static final char CORRECT = 'O';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[] score = br.readLine().toCharArray();
		
		System.out.println(getScore(N, score));
	}
	
	private static int getScore(int n, char[] arr) {
		int total = 0;
		int bonus = 0;
		
		for(int i = 0; i < n; i++) {
			if(arr[i] == CORRECT) {
				total += (i + 1) + bonus;
				bonus++;
			}
			else {
				bonus = 0;
			}
		}
		
		return total;
	}
}
