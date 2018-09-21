package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10804번: 카드 역배치
 *
 *	@see https://www.acmicpc.net/problem/10804/
 *
 */
public class Boj10804 {
	private static final char SPACE = ' ';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = 10;
		
		int[] nums = new int[21];
		for(int i = 1; i < nums.length; i++) {
			nums[i] = i;
		}
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			int idx = 0;
			int loop = (to + from) / 2;
			
			for(int i = from; i < loop + 1; i++) {	// 각 범위별로 숫자카드를 뒤집어줌
				int tmp = nums[i];
				nums[i] = nums[to - idx];
				nums[to - idx++] = tmp;
			}
		}
		
		StringBuilder sb = new StringBuilder();				// 최종 정렬된 카드를 버퍼에 담고
		for(int i = 1; i < nums.length; i++) sb.append(nums[i]).append(SPACE);
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}
