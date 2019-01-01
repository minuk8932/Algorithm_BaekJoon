package two_pointer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1253번: 좋다
 *
 *	@see https://www.acmicpc.net/problem/1253/
 *
 */
public class Boj1253 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] seq = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			seq[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(seq);
		System.out.println(twoPointer(N, seq));
	}
	
	private static int twoPointer(int n, long[] arr) {
		int counts = 0;
		
		for(int idx = 0; idx < n; idx++) {
			long target = arr[idx];
			int end = n - 1;
			
			boolean hasPair = false;
			
			for(int start = 0; start < n; start++) {
				if(isSame(idx, start)) continue;
				
				while(end >= 0 && arr[start] + arr[end] >= target) { 		// 정렬된 값들이므로 합이 타겟보다 작아지면 더이상 탐색할 필요 없음           
					if(!isSame(end, start) && !isSame(end, idx)) {	
						if(isSame(target, arr[start] + arr[end])) hasPair = true;
					}
					
					end--;
				}
			}
			
			if(hasPair) counts++;		// 타겟을 답으로 띄우는 서로다른 세 인덱스가 존재
		}
		
		return counts;
	}
	
	private static boolean isSame(long a, long b) {
		if(a == b) return true;
		else return false;
	}
}
