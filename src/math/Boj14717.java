package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14717번: 앉았다
 *
 *	@see https://www.acmicpc.net/problem/14717/
 *
 */
public class Boj14717 {
	private static final Double TOTAL = 153.0;			// 18C2

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		winRate(A, B);
	}
	
	private static void winRate(int a, int b) {
		double value = 0;
		
		if(a == b) {
			value = 1 - (10 - a) / TOTAL;			// 땡으로 이길 확률
		}
		else {
			boolean[] used = new boolean[11];
			
			double sum = (a + b) % 10;
			used[a] = used[b] = true;
			
			int head = 0;
			for(int i = 1; i < 11; i++) {
				for(int j = i + 1; j < 11; j++) {
					if(sum <= (i + j) % 10 || (used[i] && used[j])) continue;		// 걸러내기
					
					if(!used[i] && !used[j]) head += 4;
					else head += 2;
				}
			}
			
			value = head / TOTAL;			// 끗으로 이길 확률
		}
		
		System.out.println(String.format("%.3f", value));
	}
}
