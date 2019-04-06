package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17127번: 벚꽃이 정보섬에 피어난 이유
 *
 *	@see https://www.acmicpc.net/problem/17127/
 *
 */
public class Boj17127 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] trees = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getSum(N, trees));
	}
	
	private static int getSum(int n, int[] arr) {
		int max = 0;
		int set = n - 4 + 1;
		
		for(int i = 0; i < n; i++) {
			int bundle = 1;
			int sum = 0;
			
			if(i + set > n) break;
			for(int k = i; k < i + set; k++) {		// 간격 조정 후 계산
				bundle *= arr[k];	
			}
			
			for(int j = 0; j < n; j++) {
				if(j < i || j >= i + set) {
					sum += arr[j];
				}
			}
			
			max = Math.max(max, sum + bundle);
		}
		
		return max;
	}
}
