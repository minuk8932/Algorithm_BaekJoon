package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 17252번: 삼삼한 수
 *
 *	@see https://www.acmicpc.net/problem/17252/
 *
 */
public class Boj17252 {
	private static final int[] POWS = new int[20];
	
	private static boolean[] visit;
	private static boolean flag;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < POWS.length; i++) {		// 3의 지수승 미리 저장
			POWS[i] = (int) Math.pow(3, i);
		}
		
		for(int i = 0; i < POWS.length; i++) {
			if(flag) break;							// 삼삼한 수면 끝낸다
			
			visit = new boolean[POWS.length];
			backTracking(N, i, POWS[i]);
		}
		
		System.out.println(flag ? "YES": "NO");
	}
	
	private static void backTracking(int target, int current, long value) {
		if(visit[current] || flag) return;
		visit[current] = true;
		
		if(value > target) return;
		
		if(value == target) {
			flag = true;
			return;
		}
		
		for(int next = current + 1; next < POWS.length; next++) {
			if(visit[next]) continue;
			
			backTracking(target, next, value + POWS[next]);			// 하나씩 더해보면서 삼삼한 수인지 체크
			visit[next] = false;
		}
	}
}
