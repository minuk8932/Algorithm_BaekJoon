package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6603번: 로또
 *
 *	@see https://www.acmicpc.net/problem/6603/
 *
 */
public class Boj6603 {
	private static StringBuilder sb = new StringBuilder();
	private static int k = 0;
	private static int[] nums = null;
	private static ArrayList<Integer> n = new ArrayList<>();
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			
			if(k == 0) break;		// 반복문 종료 조건
			
			nums = new int[k];
			for(int i = 0; i < k; i++) nums[i] = Integer.parseInt(st.nextToken());
			
			n = new ArrayList<>();
			dfs(0);					// 깊이 우선 탐색 메소드 실행
			
			sb.append(NEW_LINE);	// 테스트 케이스 간 개행 처리
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 깊이 우선 탐색 메소드
	 * 
	 */
	private static void dfs(int start) {
		if(start == k) return;		// 중첩 횟수가 k와 같은 경우 깊이 우선 탐색 종료
		
		n.add(nums[start]);			// start에 해당하는 배열 내의 값을 리스트에 저장
		
		if(n.size() == 6) {
			for(int i = 0; i < 6; i++) {			// 리스트 크기가 적합한 크기가 된 경우
				sb.append(n.get(i)).append(SPACE);		// 버퍼에 해당 리스트의 내용을 담아줌
			}
			sb.append(NEW_LINE);
		}
		
		dfs(start + 1);			// 다음번째 숫자를 재귀 호출
		n.remove(n.size() - 1);		// 만약 재귀호출이 끊긴다면, 리스트 맨뒤의 숫자를 삭제 후
		dfs(start + 1);			// 백트래킹 실행
	}
}
