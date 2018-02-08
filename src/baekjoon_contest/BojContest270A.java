package baekjoon_contest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 *	백준 contest koi4u 2008년 5월 A : 파스칼의 삼각형 
 *
 *	@see https://www.acmicpc.net/contest/view/270
 *
 */
public class BojContest270A {
	private static final int MAX = 31;
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		//버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		br.close();
		
		int[][] dp = new int[MAX][MAX];
		
		for(int i = 1; i < MAX; i++){					// n : 30까지 파스칼 삼각형 입력
			for(int j = 1; j < i + 1; j++){
				if(j == 1 || j == i){
					dp[i][j] = 1;
				}
				else{
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				}
			}
		}
		
		int res = 0;
		int cnt = 1;
		
		for(int i = R; i < R + W; i++){				// R C W에 따른 총합 계산
			for(int j = C; j < C + cnt; j++){
				res += dp[i][j];
			}
			cnt++;
		}
		
		System.out.println(res);						// 결과 값 출력 
	}
}
