package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16162번: 가희와 3단 고음
 *
 *	@see https://www.acmicpc.net/problem/16162/
 *
 */
public class Boj16162 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int[] part = new int[N];
		int idx = -1;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			part[i] = Integer.parseInt(st.nextToken());
			
			if(idx == -1 && part[i] == A) idx = i;		// 첫항이 나타나는 가장 앞의 인덱스
		}
		
		System.out.println(idx == -1 ? 0 : lis(N, part, A, D, idx));		// 첫항이 없으면 0 아니면 최대 단수 출력
	}
	
	private static int lis(int n, int[] arr, int target, int d, int start) {
		int count = 1;
		
		for(int i = start + 1; i < n; i++) {
			if(arr[i] == target + d) {		// 다음 항이 존재하면
				count++;					// 항의 길이 +1, 존재한 항의 값으로 변경
				target += d;
			}
		}
		
		return count;
	}
}
