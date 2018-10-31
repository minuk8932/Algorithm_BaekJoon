package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16198번: 에너지 모으기
 *
 *	@see https://www.acmipc.net/problem/16198/
 *
 */
public class Boj16198 {
	private static int N = 0;
	private static int res = 0;
	private static ArrayList<Integer> arr = null;
	private static boolean[] isUsed = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new ArrayList<>();
		isUsed = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) arr.add(Integer.parseInt(st.nextToken()));
		
		dfs(0, 0);					// 깊이 우선 탐색 메소드
		System.out.println(res);	// 결과 값 출력
	}
	
	/**
	 * 깊이 우선 탐색 메소드
	 * @param size: 종료조건 확인
	 * @param sum: 최댓값 저장
	 */
	private static void dfs(int size, int sum) {
		if(size == N - 2) {			// 원소가 2개 남았을 경우 결과 변수에 총합을 저장 후 메소드 종료
			if(sum > res) res = sum;
			return;
		}
		
		for(int i = 1; i < N - 1; i++) {
			if(!isUsed[i]) {
				isUsed[i] = true;
				
				dfs(size + 1, sum + getValue(i));	// 재귀 호출시 getValue 메소드를 통해 양 쪽에 존재하는 값의 곱을 합하여 실행
				isUsed[i] = false;
			}
		}
	}
	
	/**
	 * 양쪽의 곱을 구하는 메소드
	 * @param idx: 기준일 될 인덱스
	 * @return 곱
	 */
	private static int getValue(int idx) {
		int pre = 0;
		int post = 0;
		
		for(int i = idx - 1; i >= 0; i--) {		// 인덱스 기준 가장 가까운 왼쪽의 값
			if(isUsed[i]) continue;
			
			pre = arr.get(i);
			break;
		}
		
		for(int i = idx + 1; i < N; i++) {		// 인덱스 기준 가장 가까운 오른쪽의 값
			if(isUsed[i]) continue;
			
			post = arr.get(i);
			break;
		}
		
		return pre * post;
	}
}
