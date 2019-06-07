package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17251번: 힘 겨루기
 *
 *	@see https://www.acmicpc.net/problem/17251/
 *
 */
public class Boj17251 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] player = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			player[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getWinner(N, player));
	}
	
	private static char getWinner(int n, int[] arr) {		
		int max = 0;
		for(int i = 0; i < n; i++) {
			if(arr[i] > max) max = arr[i];
		}
		
		int left = n;
		int right = 0;
		for(int i = 0; i < n; i++) {
			if(arr[i] == max) {
				left = Math.min(left, i);			// B팀이 이기는 최대 횟수
				right = Math.max(right, i);			// R팀이 이기는 최대 횟수
			}
		}
		
		right = n - right - 1;
		
		if(left == right) return 'X';
		else return left > right ? 'B': 'R';
	}
}
