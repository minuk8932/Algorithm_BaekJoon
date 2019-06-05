package sort;
import java.util.Scanner;

/**
 * 
 * 	@author minchoba
 *	백준 17263번: Sort 마스터 배지훈
 *
 *	@see https://www.acmicpc.net/problem/17263/
 *
 */
public class Boj17263 {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			int value = sc.nextInt();
			if(value > max) max = value;
		}
		
		System.out.println(max);
	}
}
