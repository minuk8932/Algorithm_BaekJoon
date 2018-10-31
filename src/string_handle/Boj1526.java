package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1526번: 가장 큰 금민수
 *
 *	@see https://www.acmicpc.net/problem/1526/
 *
 */
public class Boj1526 {
	private static final int INF = 1_000_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[] isKMS = new boolean[INF];
		for(int i = 0; i < INF; i++) {
			String num = String.valueOf(i);
			
			if(getKMS(num)) isKMS[i] = true;		// getKMS이 참이면 해당번째 값을 참으로 저장
		}
		
		int res = 0;
		
		for(int i = 0; i <= N; i++) {
			if(!isKMS[i]) continue;		// 참인 배열중 N이하의 가장 큰 수를 결과에 저장
			res = i;
		}
		
		System.out.println(res);		// 결과 값 출력
	}
	
	/**
	 * 금민수 여부 체크 메소드
	 * 	4, 7만 소지한 수일때 참을 반환
	 */
	private static boolean getKMS(String str) {
		return !str.contains("0") && !str.contains("1") && !str.contains("2") && !str.contains("3")
				&& !str.contains("5") && !str.contains("6") && !str.contains("8") && !str.contains("9")
				? true : false;
	}
}
