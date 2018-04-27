package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 2581번  : 소수
 *
 *	@see https://www.acmicpc.net/problem/2581/
 *
 */
public class Boj2581 {
	private static final String END_LINE = "\n";
	private static final int INF = 10_001;
	
	private static int N = 0;
	private static int M = 0;
	private static boolean[] isPrime = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		M = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		isPrime = new boolean[INF];						// 소수인지 판별할 진리 배열
		Arrays.fill(isPrime, true);							// 모두 참 값으로 채워준 후
		int root = (int) Math.sqrt(isPrime.length);	// 에라토스테네스의 체 사용을 위한 최대 수 설정
		
		
		for(int i = 2; i < root + 1; i++){						// 에라토스테네스의 체를 돌려서
			for(int j = i; j < isPrime.length; j++){
				int idx = i * j;
				
				if(idx < INF){
					isPrime[idx] = false;						// 최댓값의 제곱근인 100까지의 배수들을 찾아서 모두 거짓으로 바꿔줌
				}
			}
		}
		
		System.out.println(result());						// 결과 메소드를 통한 값 출력
	}
	
	/**
	 * 	결과 반환 메소드
	 * 	@return: 버퍼에 담긴 값들을 문자열 형태로 반환
	 */
	private static String result(){
		StringBuilder sb = new StringBuilder();
		int sum = 0, min = 0;
		
		isPrime[1] = false;				// 1은 소수가 아니므로 만약 들어올 수 있는 입력을 대비해 거짓으로 초기화
		
		for(int i = M; i < N + 1; i++){
			if(isPrime[i]){					// 소수가 참이라고 되어있는 인덱스만 가져와서
				if(sum == 0){				// 그전에 맨처음 소수(가장작은)를 변수 min에 담고
					min = i;
				}
				
				sum += i;					// 소수의 값을 모두 합해줌
			}
		}
		
		sb.append(sum == 0 ? -1 : sum + END_LINE + min);		// 소수가 없으면 -1, 그외에는 소수들의 총 합과 그 중 제일 작은 소수를 반환한다.
		
		return sb.toString();
	}
}
