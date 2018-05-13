package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * 
 * @author minchoba
 *	백준 11567번: 2018 연세대학교 프로그래밍 경진대회
 *
 *	@see https://www.acmicpc.net/problem/15667/
 *
 */
public class Boj15667 {
	public static void main(String[] args)throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()) - 1;	// 대형 폭죽의 폭발 수 제외
		int res = 0;
		
		for(int i = 1; i < N; i++){
			if(N == i + Math.pow(i, 2)){		// 중형 폭죽의 폭발 수 + 소형 폭죽의 폭발 수의 값과 일치하는 값을 찾음
				res = i;				
				break;
			}
		}
		
		System.out.println(res);		// 결과값 출력
	}
}
