package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16943번: 숫자 재배치
 *
 *	@see https://www.acmicpc.net/problem/16943/
 *
 */
public class Boj16943 {
	private static boolean[] visit;
	private static int[] seq;
	private static int result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		char[] wordA = st.nextToken().toCharArray();
		int B = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < wordA.length; i++) {
			visit = new boolean[wordA.length];
			seq = new int[wordA.length];
			
			backTracking(wordA, B, 0, i);
		}
		
		System.out.println(result == 0 ? -1: result);
	}
	
	private static void backTracking(char[] arr, int target, int count, int current) {
		if(visit[current]) return;
		visit[current] = true;
		
		seq[count] = arr[current] - '0';
		
		if(count == arr.length - 1) {
			if(seq[0] != 0) {
				int value = 0;
				
				for(int i = 0; i < seq.length; i++) {
					value += seq[i] * Math.pow(10, seq.length - 1 - i);		// 재배치된 숫자 생성
				}
				
				if(target >= value) result = Math.max(result, value);		// 타겟보다 작은 최댓값
			}
			
			return;
		}
		
		for(int next = 0; next < arr.length; next++) {
			if(visit[next]) continue;
			
			backTracking(arr, target, count + 1, next);
			visit[next] = false;
		}
	}
}
