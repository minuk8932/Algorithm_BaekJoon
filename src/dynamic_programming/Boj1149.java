package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1149 {
	private static final String SPACE = " ";
	private static final int COLOR_NUMBER = 3;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // The number of houses
		int[][] RGB = new int[N+3][3];
		long[][] dp = new long[N+3][3];

		/*
		 * cost - RGB[i][0] : red, RGB[i][1] : green, RGB[i][2] : blue
		 */
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);

			for (int j = 0; j < COLOR_NUMBER; j++) {
				RGB[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		// 1의 경우, 고정 값 입력
		dp[1][0] = RGB[1][0];
        dp[1][1] = RGB[1][1];
        dp[1][2] = RGB[1][2];
        
        // 배열 2와 i번째 값을 선택하면, RGB 1에서 i번째를 제외한 두 값의 최솟값(이웃의 중복 제거)을 비교하여 뽑은 후
        // RGB[2][i]와 합 하여 dp[2][i]에 입력
        dp[2][0] = RGB[2][0] + Math.min(dp[1][1], dp[1][2]);
        dp[2][1] = RGB[2][1] + Math.min(dp[1][0], dp[1][2]);
        dp[2][2] = RGB[2][2] + Math.min(dp[1][0], dp[1][1]);
        
        // 위와 마찬가지로 나머지 배열들도 똑같이 처리해 준다.(dp 최소비용 구하기 공식)
        for(int i = COLOR_NUMBER; i <= N; i++){
            dp[i][0] = RGB[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = RGB[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = RGB[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }
        
        /*
         * 		최종 Array of dp
         * 		
         * 		26	40	83
         * 		89	86	83
         * 		96	172	185
         * 		-> 각 3행의 값은 첫번째 두번째 세번째 집에 3가지 색을 색칠하는 각각의 경우(6가지) 중 최소 비용을 의미 (이웃간 색의 중복 없이)
         */

        // 결과를 받을 res 변수
        long res = 0;
        
        // 3가지 색이므로 각각 색깔 별 집의 cost를 비교해 res에 저장
        res = Math.min(dp[N][0], dp[N][1]);
        res = Math.min(res, dp[N][2]);

        // res 출력
        System.out.println(res);
		
		
	}
}
