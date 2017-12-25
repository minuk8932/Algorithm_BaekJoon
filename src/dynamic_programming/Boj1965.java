package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 *	백준 1965번 : 상자 넣기
 *
 *	@see https://www.acmicpc.net/problem/1965
 *
 */

public class Boj1965 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] box = new int[n + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 1; i < n + 1; i++){
			box[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[n + 1];

		for(int i = 1; i < n + 1; i++){
			for(int j = 1; j < i + 1; j++){
				if(box[i] > box[j - 1]){									// 현재 박스로부터 이전 박스들끼리의 크기를 비교한 후
					dp[i] = Math.max(dp[i], dp[j - 1] + 1);		// 만약 이전 박스가 작았다면, 
																				//이전 dp 배열 값에 1을 더한 것 과 현재 박스에 해당하는 dp 배열 값을 비교하여 큰 것을 현재 dp 배열에 할당
				}
			}
		}
		int max = 0;
		
		for(int i = 1; i < n + 1; i++){									// dp 배열에 존재하는 값들 중 최댓값 출력
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}
