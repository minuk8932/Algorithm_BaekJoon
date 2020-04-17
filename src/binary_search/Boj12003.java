package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 12003번: Diamond Collector (silver)
 *
 * @see https://www.acmicpc.net/problem/12003/
 *
 */
public class Boj12003 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] diamond = new int[N];
		for(int i = 0; i < N; i++) {
			diamond[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(diamond);
		System.out.println(makeResult(N, K, diamond));
	}
	
	private static int makeResult(int n, int k, int[] arr) {
		int[] list = new int[n];

		for(int i = 0; i < n; i++){
			list[i] = binarySearch(0, n, arr[i] + k, arr) - i;		// find inner
		}

		int[] max = new int[n];
		max[n - 1] = list[n - 1] > 0 ? list[n - 1]: 0;
		for(int i = n - 2; i >= 0; i--) {
			max[i] = Math.max(max[i + 1], list[i]);
		}

		int result = 0;
		int sum, idx = 0;

		while(n > (sum = list[idx] + idx)) {									// possibility
			result = Math.max(result, list[idx] + max[sum]);
			idx++;
		}
		
		return result;
	}

	private static int binarySearch(int start, int end, int target, int[] arr) {
		while(start < end) {
			int mid = (start + end) / 2;

			if(arr[mid] <= target) start = mid + 1;
			else end = mid;
		}

		return end;
	}
}
