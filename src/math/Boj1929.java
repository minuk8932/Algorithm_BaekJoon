package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1929번: 소수 구하기
 *
 *	@see https://www.acmicpc.net/problem/1929/
 *
 */
public class Boj1929 {
	private static final String END_LINE = "\n";
	
	private static boolean[] isPrime = null;
	private static int N = 0;
	private static int M = 0;
	
	private static int INF = 1_000_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());			// 시작
		N = Integer.parseInt(st.nextToken());			// 끝
		
		isPrime = new boolean[INF];			// 소수인지 판별하는 진리 배열
		Arrays.fill(isPrime, true);				// 참으로 배열 전체 초기화
		
		getPrime();							// 소수 판별 메소드 실행
		System.out.println(result());	// 결과 출력 메소드 실행
	}
	
	/**
	 * 에라토스 테네스의 체를 이용한 소수 판별
	 */
	private static void getPrime(){
		int max = (int) Math.sqrt(N);			// 구하려는 최대 숫자의 제곱근
		
		for(int i = 2; i < max + 1; i++){			// 2부터 제곱근까지: 에라토스 테네스의 체
			if(!isPrime[i]) continue;					// 이미 값이 false(소수가 아님)로 체크되어있다면 패스
			
			for(int j = M; j < N + 1; j++){		// 체크할 수의 범위
				if(j % i == 0 && i != j){				// 나누어 떨어지거나, 같은 수로 나눈 경우가 아닐 때
					isPrime[j] = false;				// 소수가 아님으로 초기화
				}				
			}
		}
		
		isPrime[1] = false;					// 1은 소수에 해당하지 않음
	}
	
	/**
	 * 결과 출력 메소드
	 * @return: 결과를 스트링으로 반환
	 */
	private static String result(){
		StringBuilder sb = new StringBuilder();
		
		for(int prime = M; prime < N + 1; prime++){	// 범위내 숫자 중
			if(!isPrime[prime]) continue;						// 소수가 아니면 패스
				
			sb.append(prime).append(END_LINE);			// 소수인 값만 버퍼에 담고
		}
		
		return sb.toString();		// 문자열로 반환
	}
}
