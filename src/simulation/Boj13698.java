package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 13698번: Hawk Eyes
 *
 *	@see https://www.acmicpc.net/problem/13698/
 *
 */
public class Boj13698 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] query = br.readLine().toCharArray();
		
		System.out.println(shake(query));
	}
	
	private static String shake(char[] q) {
		StringBuilder sb = new StringBuilder();
		int[] balls = {1, 2, 3, 4};
		int tmp = 0;
		
		for(char c: q) {			// 컵 섞기		
			switch(c) {
			case 'A':
				tmp = balls[0];
				balls[0] = balls[1];
				balls[1] = tmp;
				break;
				
			case 'B':
				tmp = balls[0];
				balls[0] = balls[2];
				balls[2] = tmp;
				break;
				
			case 'C':
				tmp = balls[0];
				balls[0] = balls[3];
				balls[3] = tmp;
				break;
				
			case 'D':
				tmp = balls[1];
				balls[1] = balls[2];
				balls[2] = tmp;
				break;
				
			case 'E':
				tmp = balls[1];
				balls[1] = balls[3];
				balls[3] = tmp;
				break;
				
			case 'F':
				tmp = balls[3];
				balls[3] = balls[2];
				balls[2] = tmp;
				break;
			}
		}
		
		int min = 0, max = 0;
		for(int i = 0; i < 4; i++) {
			if(balls[i] == 4) max = i + 1;
			if(balls[i] == 1) min = i + 1;
		}
		
		return sb.append(min).append(NEW_LINE).append(max).toString();
	}
}
