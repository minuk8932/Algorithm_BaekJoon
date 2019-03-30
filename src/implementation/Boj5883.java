package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 5883번: 아이폰 9S
 *
 *	@see https://www.acmicpc.net/problem/5883/
 *
 */
public class Boj5883 {
	private static final int INF = 1_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] request = new int[N];
		
		for(int i = 0; i < N; i++) {
			int size = Integer.parseInt(br.readLine());
			request[i] = size;
		}
		
		System.out.println(removeRequest(N, request));
	}
	
	private static int removeRequest(int n, int[] seq) {
		boolean[] isRemoved = new boolean[INF];
		int[] count = new int[INF];
		
		for(int i = 0; i < n; i++) {
			int remove = seq[i];
			
			if(isRemoved[remove]) continue;		// 용량 별로 한번씩 제거
			isRemoved[remove] = true;
			
			int tmp = 0, value = seq[0];

			for(int j = 0; j < n; j++) {
				if(remove == seq[j]) continue;
				
				if(seq[j] == value) {			// 같은 값을 갖는 경우 갯수 +1
					tmp++;
				}
				else {							// 연속한 두 값이 서로 다르면, 갯수의 최대를 해당 배열에 저장
					if(count[seq[j]] < tmp) count[seq[j]] = tmp;
					value = seq[j];
					tmp = 1;
				}
			}
			
			if(tmp != 1) count[value] = Math.max(count[value], tmp);		// 마지막에 최대값이 갱신 안되었을 경우
		}
		
		int max = 0, justOne = 0;
		for(int i = 0; i < INF; i++) {
			if(isRemoved[i]) justOne++;		// 용량 종류가 1개인 경우
			
			if(count[i] > max) {
				max = count[i];
			}
		}
		
		return justOne == 1 ? n: max;
	}
}
