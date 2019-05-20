package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17245번: 서버실
 *
 *	@see https://www.acmicpc.net/problem/17245/
 *
 */
public class Boj17245 {
	private static final double THRESHOLD = 0.5;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] server = new int[N][N];
		
		double total = 0;
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				server[i][j] = Integer.parseInt(st.nextToken());
				total += server[i][j];
				
				if(server[i][j] > max) max = server[i][j];
			}
		}
		
		System.out.println(total == 0 ? 0: binarySearch(N, server, total, max));
	}
	
	private static int binarySearch(int n, int[][] map, double sum, int end) {
		int start = 0, time = Integer.MAX_VALUE;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			boolean flag = search(n, map, sum, mid);
			
			if(flag) {						// 50% 이상이 가동되는 경우
				end = mid - 1;
				if(mid < time) time = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		return time;
	}
	
	private static boolean search(int n, int[][] map, double sum, int timer) {
		long cooling = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == 0) continue;
				
				if(map[i][j] < timer) cooling += map[i][j];		// 가동중인 컴퓨터 갯수
				else cooling += timer;
			}
		}
		
		return cooling / sum >= THRESHOLD ? true: false;
	}
}
