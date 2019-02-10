package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16567번: 바이너리 왕국
 *
 *	@see https://www.acmicpc.net/problem/16567/
 *
 */
public class Boj16567 {
	private static final String NEW_LINE = "\n";
	private static final int[] DIRECTIONS = {1, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		boolean[] path = new boolean[N + 1];
		int count = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			if(Integer.parseInt(st.nextToken()) == 1) {
				path[i] = true;
				count = getCount(N, path, i, count);		// 인접한 더러운 땅을 확인 후 집합의 갯수 체크
			}
		}
		
		while(M-- > 0) {			
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			
			if(x == 0) {
				sb.append(count).append(NEW_LINE);
			}
			else {
				int y = Integer.parseInt(st.nextToken());
				
				if(!path[y]) {
					count = getCount(N, path, y, count);
					path[y] = true;
				}
			}
		}
		
		System.out.println(sb);
	}
	
	private static int getCount(int n, boolean[] arr, int idx, int count) {
		int adjacent = 0;
		
		for(final int DIRECTION: DIRECTIONS) {
			int next = idx + DIRECTION;
			
			if(next < 1 || next > n) continue;
			if(arr[next]) adjacent++;
		}
		
		if(adjacent == 0) count += 1;
		else if(adjacent == 2) count -= 1;
		
		return count;
	}
}
