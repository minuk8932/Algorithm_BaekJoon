package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3412번: 다트 게임
 *
 *	@see https://www.acmicpc.net/problem/3412/
 *
 */
public class Boj3412 {
	private static final char NEW_LINE = '\n';
	private static final int[] RADIAN = {400, 1600, 3600, 6400, 10000, 14400, 19600, 25600, 32400, 40000};
	private static final int[] SCORE = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int total = 0;
			
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				total += getPoint(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			sb.append(total).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int getPoint(int x, int y) {
		int dist = x * x + y * y;
		
		if(dist > RADIAN[9]) {		// 범위 벗어난 경우
			return 0;
		}
		else {
			return SCORE[almostAccess(dist)];
		}
	}
	
	private static int almostAccess(int d) {		// 감싸는 원의 반지름의 인덱스를 반환
		int idx = 0;
		
		for(int i = 0; i < RADIAN.length; i++) {
			idx = i;
			if(RADIAN[i] >= d) break;
		}
		
		return idx;
	}
}
