package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15921번: 수찬은 마린보이야!!
 *
 *	@see https://www.acmicpc.net/problem/15921/
 *
 */
public class Boj15921 {
	private static final String ARITHMETIC_EXCEPTION = "divide by zero";
	private static final String res  = "1.00";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		if(N != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
            
			for(int i = 0; i < N; i++) {
				double num = Double.parseDouble(st.nextToken());
			}
		}
		
		System.out.println(N == 0 ? ARITHMETIC_EXCEPTION : res);	// 이산 분포에서 평균과 기댓값은 같은 값을 가지므로 N에 따른 결과를 출력
	}
}
