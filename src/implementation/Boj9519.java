package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9519번: 졸려
 *
 *	@see https://www.acmicpc.net/problem/9519/
 *
 */
public class Boj9519 {
	private static char[] arr = null;
	private static String words = null;
	
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		words = br.readLine();
		arr = words.toCharArray();
		
		int s = 0;
		
		if(arr.length % 2 == 0) {		// 짝수의 경우 시작 인덱스
			s = arr.length - 3;
		}
		else {
			s = arr.length - 2;			// 홀수의 경우 시작 인덱스
		}
		
		System.out.println(blink(s, X));		// 깜빡임 메서드를 통한 결과값 출력
	}
	
	/**
	 * 깜빡 메소드
	 * @param 시작 인덱스와 반복횟수
	 * @return 반복 후 나오는 결과 문자열
	 */
	private static String blink(int start, int times) {
		StringBuilder sb = new StringBuilder();
		
		int mod = getLoop(start);		// 해당 문자열의 변형 주기를 구함		
		times %= mod;			// 주기로 나눈 나머지만큼 반복문 수행
		
		while(times-- > 0) {							// 주기로 나눈 나머지 만큼 돌리며 동작은 getLoop() 메소드와 같다
			for(int i = start; i >= 0; i -= 2) {
				char tmp = arr[i];
				
				for(int j = i + 1; j < arr.length; j++) {
					arr[j - 1] = arr[j];
				}
				arr[arr.length - 1] = tmp;
			}
		}
		
		for(int i = 0; i < arr.length; i++) {		// 최종 배열 상태를 버퍼에 담고
			sb.append(arr[i]);
		}

		return sb.toString();			// 버퍼의 값을 문자열로 반환
	}
	
	/**
	 * 주기 찾기 메소드
	 * @return 주기 반환
	 */
	private static int getLoop(int start) {
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		
		while(true) {
			for(int i = start; i >= 0; i -= 2) {		// 시작 인덱스부터 한칸씩 넘기며 문자값을 받아둔 후
				char tmp = arr[i];
				
				for(int j = i + 1; j < arr.length; j++) {	// 저장한 문자를 빼고 나머지 뒤의 문자를 모두 한칸씩 앞으로 당겨줌
					arr[j - 1] = arr[j];
				}
				arr[arr.length - 1] = tmp;			// 마지막에 저장했던 문자를 추가
			}										// -> 반복문 종료시 깜박임 1회
			
			String a = null;
			for(int i = 0; i < arr.length; i++) {		// 깜빡임 한번씩 할때마다 그때의 문자열을 저장한 후
				if(i == 0) a = String.valueOf(arr[i]);
				else a += arr[i];
			}
			
			cnt++;								// 카운팅 변수로 주기를 계산하며
			if(a.equals(words)) break;			// 본래의 문자열과 비교 -> 본래 문자와 같은것이 나오면 종료
		}
		
		return cnt;		// 주기 반환
	}
}
