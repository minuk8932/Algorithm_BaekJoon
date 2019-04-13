package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2548번: 대표 자연수
 *
 *	@see https://www.acmicpc.net/problem/2548/
 *
 */
public class Boj2548 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getRepresent(N, nums));
	}
	
	private static int getRepresent(int n, int[] arr) {
		Arrays.sort(arr);
		
		if(n % 2 == 0) {
			int[] tmp = new int[2];
			int half = n / 2;
			
			for(int i = 0; i < n; i++) {
				tmp[0] += Math.abs(arr[half] - arr[i]);
				tmp[1] += Math.abs(arr[half - 1] - arr[i]);
			}
			
			if(tmp[0] < tmp[1]) return arr[half];		// 짝수 개의 경우 합이 작은 수를 출력, 같다면 앞의 숫자 출력
			else  return arr[half - 1];
		}
		else {
			return arr[n / 2];			// 홀수 개의 경우 중앙값
		}
	}
}
