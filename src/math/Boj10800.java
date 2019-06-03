package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10800번: 컬러볼
 *
 *	@see https://www.acmicpc.net/problem/10800
 *
 */
public class Boj10800 {
	private static final String NEW_LINE = "\n";
	
	private static class Ball implements Comparable<Ball>{
		int idx;
		int color;
		int size;
		
		public Ball(int idx, int color, int size) {
			this.idx = idx;
			this.color = color;
			this.size = size;
		}
		
		@Override
		public int compareTo(Ball b) {
			if(this.size < b.size) return -1;
			else if(this.size > b.size) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Ball[] balls = new Ball[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			balls[i] = new Ball(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(catchBall(N, balls));
	}
	
	private static String catchBall(int n, Ball[] arr) {
		StringBuilder sb = new StringBuilder();
		Arrays.sort(arr);
		int[] result = new int[n];
		int[] dup = new int[200_001];
		
		int total = 0, idx = 0;
		
		for(int i = 0; i < n; i++) {
			while(arr[i].size > arr[idx].size) {
				total += arr[idx].size;					// 현재까지 총합
				dup[arr[idx].color] += arr[idx].size;	// 해당 공과 같은 색을 가지는 공의 총합
				
				idx++;
			}
			
			result[arr[i].idx] = total - dup[arr[i].color];		// 중복값 뺴기
		}
		
		for(int i = 0; i < n; i++) {
			sb.append(result[i]).append(NEW_LINE);
		}
		
		return sb.toString();
	}
}
