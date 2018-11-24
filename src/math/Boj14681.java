package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14681번: Quadrant Selection
 *
 *	@see https://www.acmicpc.net/problem/14681/
 *
 */
public class Boj14681 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(br.readLine());
		int y = Integer.parseInt(br.readLine());
		
		System.out.println(quadrantSelection(x,  y));	// 결과 출력
	}
	
	private static int quadrantSelection(int x, int y) {
		if(x * y > 0) {
			if(x > 0) return 1;
			else return 3;
		}
		else {
			if(x > 0) return 4;
			else return 2;
		}
	}
}
