package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2745번: 진법 변환
 *
 *	@see https://www.acmicpc.net/problem/2745/
 *
 */
public class Boj2745 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		char[] N = st.nextToken().toCharArray();
		int B = Integer.parseInt(st.nextToken());
		
		int res = 0;
		
		for(int i = N.length - 1; i >= 0; i--) {
			int num = 0;
			
			if(N[i] >= 'A' && N[i] <= 'Z') {	// A ~ Z 인 경우
				num = N[i] - 55;
			}
			else {					// 0 ~ 9 인 경우
				num = N[i] - 48;
			}
			
			res += (num * Math.pow(B, N.length - i - 1));		// 최종값에 진법변환 하여 더해줌
		}
		
		System.out.println(res);		// 결과값 출력
	}
}
