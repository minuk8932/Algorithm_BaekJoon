package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15727번: 조별 과제를 하려는데 조장이 사라졌다.
 *
 *	@see https://www.acmicpc.net/problem/15727/
 *
 */
public class Boj15727 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());		
		int res = L / 5;	// 몫 계산
		
		if(L - (5 * res) != 0) res++;		// 나누어 떨어지지 않았을 경우
		
		System.out.println(res);		// 최종 결과값 출력
	}
}
