package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2502번: 떡먹는 호랑이
 *
 *	@see https://www.acmicpc.net/problem/2502/
 *
 */
public class Boj2502 {
	private static final String NEW_LINE = "\n";
	
	private static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(getResult(D, K));
	}
	
	private static StringBuilder getResult(int day, int dduks) {
		Pair[] fibo = new Pair[day];
		fibo[0] = new Pair(1, 0);
		fibo[1] = new Pair(0, 1);
		
		for(int i = 2; i < day; i++) {
			fibo[i] = new Pair(fibo[i - 2].x + fibo[i - 1].x, fibo[i - 2].y + fibo[i - 1].y);		// 방정식 생성
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < dduks; i++) {
			int value = dduks - (i * fibo[day - 1].x);
			
			if(value % fibo[day - 1].y == 0) {			// 값이 나누어 떨어지는 경우 해당하는 날짜 저장
				sb.append(i).append(NEW_LINE).append(value / fibo[day - 1].y);
				break;
			}
		}
		
		return sb;
	}
}
