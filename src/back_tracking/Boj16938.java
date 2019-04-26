package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16938번: 캠프 준비
 *
 *	@see https://www.acmicpc.net/problem/16938/
 *
 */
public class Boj16938 {
	private static int N, L, R, X, result;
	private static boolean[] visit;
	
	private static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for(int i = 0; i < N; i++) {
			visit = new boolean[N];
			backTracking(i, 1);			// 가능한 조합 모두 탐색
		}
		
		System.out.println(result);
	}
	
	private static void backTracking(int current, int count) {
		if(count > N) return;			// N개까지 선택한 경우
		
		if(visit[current]) return;
		visit[current] = true;		
		
		if(count >= 2) {
			if(sum() && differ()) result++;		// 합과 최대 - 최소가 조건에 부합하는 경우
		}
		
		for(int next = current + 1; next < N; next++) {
			if(visit[next]) continue;
			
			backTracking(next, count + 1);
			visit[next] = false;
		}
	}
	
	private static boolean sum() {
		int sum = 0;
		
		for(int i = 0; i < N; i++) {
			if(!visit[i]) continue;
			sum += arr[i];
		}
		
		return sum >= L && sum <= R ? true: false;
	}
	
	private static boolean differ() {
		int min = Integer.MAX_VALUE;
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			if(!visit[i]) continue;
			if(max < arr[i]) max = arr[i];
			if(min > arr[i]) min = arr[i];
		}
		
		return max - min >= X ? true: false;
	}
}
