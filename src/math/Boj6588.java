package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 6588번: 골드바흐의 추측
 *
 *	@see https://www.acmicpc.net/problem/6588/
 *
 */
public class Boj6588 {
	private static final int INF = 1_000_001;
	private static final String NEW_LINE = "\n";
	private static final String PLUS = " + ";
	private static final String EQUAL = " = ";
	private static final String WRONG = "Goldbach's conjecture is wrong.";
	
	private static boolean[] prime = new boolean[INF];
	private static Pair p = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Arrays.fill(prime, true);				// 소수 판별 배열을 모두 참으로 변경
		isPrime();							// 소수 걸러내기 메소드
		
		while(true) {
			int n = Integer.parseInt(br.readLine());		// 골드바흐의 파티션을 찾아 낼 수 있는 경우
			if(n == 0) break;							// 0이 입력으로 들어오면 반복문 종료
			
			for(int i = n - 1; i >= n / 2; i--) {		// n에서 해당수의 n/2까지 돌려가면서
				int diff = n - i;							// n과 i의 차이점을 찾고
				
				if(!prime[i] || !prime[diff]) continue;		// 그 수 중 소수가 아니라면 다음 수로 넘기고
				p = new Pair(diff, i);						// 소수가 맞다면 파티션 최대 최소 짝을 크기에 맞게 담아줌
				
				if(p != null) break;		// Pair에 짝이 저장되었다면 바로 종료
			}
			
			try {									// 해당하는 소수가 존재하는 경우
				sb.append(n).append(EQUAL).append(p.min).append(PLUS).append(p.max).append(NEW_LINE);
			}
			catch(NullPointerException npe) {		// NullPointerException의 경우, 즉 해당하는 소수가 없을 때
				sb.append(WRONG).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 	최대 최소의 짝을 저장 할 이너클래스
	 * 	@author minchoba
	 *	
	 */
	private static class Pair{
		int min;
		int max;
		
		public Pair(int min, int max) {
			this.max = max;
			this.min = min;
		}
	}
	
	private static void isPrime() {			// 소수 찾기 메소드
		prime[0] = prime[1] = false;		// 0, 1은 소수가 아님
		
		for(int i = 2; i < INF; i++) {		// 2 ~ 10000 까지 숫자 중
			if(!prime[i]) continue;				// 소수가 아닌것으로 판별된 수는 넘기고
			
			for(int j = i + i; j < INF; j += i) {	// 소수가 아닌 수들을 판별해
				prime[j] = false;					// 거짓으로 바꿔줌
			}
		}
	}
}
