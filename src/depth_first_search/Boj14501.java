package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14501번 : 퇴사
 *
 *	@see https://www.acmicpc.net/problem/14501/
 *
 */
public class Boj14501 {
	private static int N = 0;
	private static int max = 0;
	private static int[] during = null;
	private static int[] resrv = null;

	private static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		resrv = new int[N];
		during = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			during[i] = Integer.parseInt(st.nextToken());								// 기간 저장
			resrv[i] = Integer.parseInt(st.nextToken());								// 날짜별 얻는 이득
		}

		dfs(0, 0, 0);						// dfs 메소드 실행

		System.out.println(max);	// 최댓값 출력
	}

	/**
	 * 퇴사시 얻는 이익 계산 메소드
	 * @param day : 기간
	 * @param profit : 실시한 상담날짜별 얻을 수 있는 이익의 합
	 * @param currentCost : 현 날짜에 상담 받을 시 얻은 이익
	 */
	public static void dfs(int day, int profit, int currentCost) {
		if (day >= N) {			// 만약 날짜가 최대 기한보다 크거나 같을때
            profit = (day == N ? profit : profit - currentCost);	// 그 기한이 N에 딱 떨어지면 해당 이익을, N보다 크면 마지막으로 얻은 이익을 뺀 값을 총 이익에 담아준다.
            
            max = Math.max(max, profit);									// 이익중 최댓값을 받고 메소드 종료
            return;
        }
		
        dfs(day + during[day], profit + resrv[day], resrv[day]); 	//	상담을 하는 경우 
        dfs(day + 1 ,profit ,currentCost);									// 상담을 하지 않는 경우
	}

}
