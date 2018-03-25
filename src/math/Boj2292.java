package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2292번 : 벌집
 *
 *	@see https://www.acmicpc.net/problem/2292/
 *
 */
public class Boj2292 {
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());
		long idx = 1, six = 1;
		int res = 1;

		while(true){
			if(idx >= N){		// 해당숫자 범위밖으로 나가면 반복문 종료
				break;
			}
			
			six = 6 * (res++);	// 등차수열로 올라가므로 6을 곱하면서 배수 변수에 넣고
			idx += six;				// 범위 변수에 배수 변수를 더해가면서 반복문을 정지할지 체크
		}
		
		System.out.println(res);		// 최종 결과값, 즉 몇번째 등차수열에 속하는 수인지 출력
	}
}
