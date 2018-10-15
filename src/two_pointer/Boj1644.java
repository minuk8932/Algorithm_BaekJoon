package two_pointer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 1644번: 소수의 연속합
 *
 *	@see https://www.acmicpc.net/problem/1644/
 *
 */
public class Boj1644 {
	private static final int INF = 4_000_001;
	
	private static boolean[] isPrime = new boolean[INF];
	private static int[] seq = new int[INF];
	private static int idx = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int N = Integer.parseInt(br.readLine());
		prime();									// 프라임 메소드를 통한 소수 구하기
		
		Pointer ptr = new Pointer(0, 0);
		int res = 0;
		int sum = 0;
		
		while(ptr.end <= idx) {            			// two pointer 알고리즘 실행
			if(sum < N) {
                sum += seq[ptr.end++];
                continue;
            }
			
			if(sum == N) res++;
			sum -= seq[ptr.start++];
		}
		
		System.out.println(res);				// 합해서 N이되느 경우의 수 출력
	}
	
	/**
	 * 포인터 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Pointer{
		int start;
		int end;
		
		public Pointer(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	/**
	 * 소수를 구하는 메소드
	 * 
	 */
	private static void prime() {
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		for(int i = 2; i < INF; i++) {				// 에라토스테네스의 체
			if(!isPrime[i]) continue;
			
			for(int j = i + i; j < INF; j += i) {
				isPrime[j] = false;
			}
		}
		
		for(int i = 0; i < INF; i++) {			// 구해둔 소수를 연속 배열에 담아줌
			if(isPrime[i]) seq[idx++] = i;
		}
	}
}
