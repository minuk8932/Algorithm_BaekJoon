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
	private static final int INF = 101;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		double avg = 0;
		double exp = 0;
		String res = "";
		
		double[] cnt = new double[INF];
		
		if(N != 0) {				// 수찬이가 연습을 한경우
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				double num = Double.parseDouble(st.nextToken());
				cnt[(int) num]++;			// 연습 기록에 따른 갯수를 저장
				avg += (num / N);			// 연습 기록의 평균
			}
			
			for(int i = 0; i < INF; i++) {
				if(cnt[i] != 0) {			// 연습 기록의 기댓값
					exp += i * (cnt[i] / N);
				}
			}
			
			res = String.valueOf(Math.round((avg / exp) * 100) / 100);		// 소수 3째자리 반올림 값
			int leng = res.length();										// 만약 결과가 소숫점이 부족한 경우
			res = leng < 4 ? leng == 1 ? res + ".00" : res + "0" : res;		// 길이가 1인 경우 .00을 2인 경우 .0을 담아줌
		}
		
		System.out.println(N == 0 || avg == 0 || exp == 0 ? ARITHMETIC_EXCEPTION : res);	// 조건에 따라 0으로 나누는 경우와 결과값을 구분해 출력
	}
}
