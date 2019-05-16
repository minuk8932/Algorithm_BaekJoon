package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17204번: 죽음의 게임
 *
 *	@see https://www.acmicpc.net/problem/17204/
 *
 */
public class Boj17204 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] player = new int[N];
		for(int i = 0; i < N; i++) {
			player[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(count(N, K, player));
	}
	
	private static int count(int n, int k, int[] arr) {
		boolean[] visit = new boolean[n];
		int count = 0, target = 0;
		
		while(target != k) {					// 보성이가 걸릴때 까지만 반복
			if(visit[target]) return -1;		// 사이클 존재시: 보성이가 걸릴 수 없음
			visit[target] = true;
			
			target = arr[target];
			count++;
		}
		
		return count;
	}
}
