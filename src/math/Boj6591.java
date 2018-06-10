package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6591번: 이항 쇼다운
 *
 *	@see https://www.acmicpc.net/problem/6591/
 *
 */
public class Boj6591 {
	private static final String NEW_LINE = "\n";
	private static final int INF = 10000;
	
	private static int[][] num = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			if(n == 0 && k == 0) {		// n, k가 0 0이 들어오면 반복문 종료
				break;
			}
			
			if(k > n / 2) {			// nCr == nCk-r의 정의에 따라 더 작은 값으로 변경
				k = n - k;
			}
			
			if(n < INF){							// 적당히 배열내에서 계산 가능한 경우
				num = new int[n + 1][k + 1];
				sb.append(combine(n, k)).append(NEW_LINE);	// 조합 메소드를 통한 결과값을 버퍼에 담아줌
			}
			else {									// 숫자가 너무 커 메모리 초과 에러가 발생할 가능성이 있는 경우
				long res = 1, loop = k, origin = n;
				
				while(loop-- > 0) {				// n부터 n - k까지의 값을 곱한 값을 res에 담고
					res *= origin;
					origin--;
				}
				
				loop = k;
				while(loop-- > 1) {				// res에서 k부터 1까지 값을 나누어줌
					res /= k;
					k--;
				}
				
				sb.append(res).append(NEW_LINE);		// 그때의 결과값 버퍼에 저장,
			}											// 실제로 1000만 들어와도 k=4 부터 int 범위를 넘어가므로 단시간 내에 해결 가능
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 조합 메소드
	 * @param nCr
	 * @return nCr의 결과값
	 */
	private static int combine(int n, int r) {		
		if(n == r || r == 0) return 1;			// 조합의 정리에 의해 nCn, nC0 = 1
		if(num[n][r] > 0) return num[n][r];		// 결과값이 nCr 배열에 할당된 경우 해당 값을 반환
		
		return num[n][r] = combine(n-1, r-1) + combine(n-1, r);	// 조합의 정의에 의해 nCr = n-1Cr-1 + n-1Cr
	}
}
