package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1500번: 최대 곱
 *
 *	@see https://www.acmicpc.net/problem/1500/
 *
 */
public class Boj1500 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long S = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		long res = 1;
		long val = S / K;			// 몫을 구하고
		long diff = S - (K * val);	// 나누어 떨어진 경우 0, 아닌 경우 나머지
		
		 for (int i = 0; i < K - diff; i++) {	// 두 경우 모두 결과값에 곱연산
             res *= val;
		 }
		 
		 for (int i = 0; i < diff; i++) {	// 나누어 떨어지지 않은 경우만 따로 추가 연산
             res *= (val + 1);
		 }
		
		System.out.println(res);		// 결과값 출력
	}
}
