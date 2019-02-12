package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3020번: 개똥벌레
 *
 *	@see https://www.acmicpc.net/problem/3020/
 *
 */
public class Boj3020 {
	private static final int INF = 500_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[] block = new int[INF];
		int[] bell = new int[INF];
		
		for(int i = 0; i < N; i++) {
			if(i % 2 == 0) {
				block[Integer.parseInt(br.readLine())]++;		// 길이 n인 석순 갯수
			}
			else {
				bell[Integer.parseInt(br.readLine())]++;		// 길이 n인 종유석 갯수 
			}
		}

		System.out.println(binarySearch(N, H, block, bell));
	}
	
	private static StringBuilder binarySearch(int n, int h, int[] block, int[] bell) {
		StringBuilder sb = new StringBuilder();
		block = getArraySum(h, block);
		bell = getArraySum(h, bell);
		
		int min = getMinCount(h, block, bell, Integer.MAX_VALUE, true);
		int count = getMinCount(h, block, bell, min, false);
		
		return sb.append(min).append(" ").append(count);
	}
	
	private static int[] getArraySum(int h, int[] arr) {
		for(int height = h; height > 1; height--) {			// 끝에서부터 해당 길이 보다 긴 종유석 또는 석순의 갯수를 더하여 저장
			arr[height - 1] += arr[height];
		}
		
		return arr;
	}
	
	private static int getMinCount(int h, int[] arr1, int[] arr2, int min, boolean getMin) {
		int count = 0;
		
		for(int i = 1; i < h + 1; i++) {			// 석순은 길이 1부터 종유석은 길이 H - 1부터 -> 즉 2개를 합하여 벽을 생성
			int sum = arr1[i] + arr2[h + 1 - i];
			
			if(getMin) {
				if(min > sum) min = sum;			// 부수는 벽의 최소
			}
			else {
				if(min == sum) count++; 			// 부수는 벽의 최소 갯수의 경우의 수
			}
		}
		
		return getMin ? min : count;
	}
}
