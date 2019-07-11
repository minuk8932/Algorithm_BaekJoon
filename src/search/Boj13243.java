package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 13243번: Non-decreasing subsegment
 *
 *	@see https://www.acmicpc.net/problem/13243
 *
 */
public class Boj13243 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] list = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getSegments(N, list));
	}
	
	private static String getSegments(int n, int[] arr) {
		StringBuilder sb = new StringBuilder();
		
		ArrayList<Long>[] sets = new ArrayList[100_001];
		for(int i = 0; i < sets.length; i++) {
			sets[i] = new ArrayList<>();
		}
		
		int head = arr[0], leng = 1, max = 0;
		long sum = arr[0];
		
		for(int i = 1; i < n; i++) {
			if(head <= arr[i]) {			// non decreasing의 경우
				leng++;
				sum += arr[i];
			}
			else {							// decreasing의 경우 값 갱신
				sets[leng].add(sum);
				max = Math.max(max, leng);
				
				leng = 1;
				sum = arr[i];
			}
			
			head = arr[i];
		}
		
		max = Math.max(leng, max);			// 마지막에 갱신이 안 될 경우
		sets[max].add(sum);
		
		return sb.append(max).append(SPACE).append(sets[max].get(0)).toString();
	}
}
