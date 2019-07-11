package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * 	@author exponential-e
 *	백준 14629번: 숫자 조각
 *
 *	@see https://www.acmicpc.net/problem/14629/
 *
 */
public class Boj14629 {
	private static final long INF = 10_000_000_000L;
	private static final long LIMIT = 9_876_543_210L;
	
	private static ArrayList<Long> values = new ArrayList<>();
	private static long min = Long.MAX_VALUE;
	private static boolean[] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		
		System.out.println(getSmaller(N));
	}
	
	private static long getSmaller(long n) {
		if(n >= INF) return LIMIT;					// INF 이상은 만들 수 없음
		
		for(int start = 1; start < 10; start++) {	// 가능한 숫자 조합들
			visit = new boolean[10];
			backTracking(n, start, start);
		}
		
		long res = Long.MAX_VALUE;
		for(long v: values) {
			long diff = Math.abs(n - v);
			if(diff == min) res = Math.min(v, res);	// 결과
		}
		
		return res;
	}
	
	private static void backTracking(long target, long val, int current) {
		if(visit[current]) return;
		visit[current] = true;
		
		values.add(val);
		if(Math.abs(target - val) < min) min = Math.abs(target - val);
		
		for(int next = 0; next < 10; next++) {
			if(visit[next]) continue;
			
			backTracking(target, val * 10 + next, next);
			visit[next] = false;
		}
	}
}
