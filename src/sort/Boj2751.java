package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2751번: 수 정렬하기 2
 *
 *	@see https://www.acmicpc.net/problem/2751/
 *
 */
public class Boj2751 {
	private static final int INF = 2_000_002;
	private static final int POSITIVE = 1_000_001;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] nums = new boolean[INF];
		
		while(N-- > 0) {
			int idx = Integer.parseInt(br.readLine());
			
			idx = idx < 0 ? idx * (-1) : idx + POSITIVE;		// 음수 양수를 나누어서 인덱스 구분
			nums[idx] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = POSITIVE - 1; i > 0; i--) {				// 음수: 역순 탐색 후 버퍼에 담음
			if(nums[i])
				sb.append(-i).append(NEW_LINE);
		}
		
		for(int i = POSITIVE; i < INF; i++) {				// 양수: 정방향 탐색 후 버퍼에 담음
			if(nums[i])
				sb.append(i - POSITIVE).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
}
