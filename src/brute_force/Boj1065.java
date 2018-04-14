package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1065번 : 한수
 *
 *	@see https://www.acmicpc.net/problem/1065/
 *
 */
public class Boj1065 {	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		System.out.println(hanSoo(num));				// 한수 메소드를 이용해 결과값 출력
	}
	
	/**
	 * 한수 갯수 계산 메소드
	 * @param num : 초기 입력 값
	 * @return	: 최종 한수의 갯수를 출력
	 */
	public static int hanSoo(int num){
		int cnt = 99;								// 자연수 99까지는 모두 한수이므로 저장 해 두고
		
		if(num < 100){							// 100보다 작은경우? 그 수까지 갯수가 그대로 한수의 수
			return num;
		}
		
		for(int i = 100; i < num + 1; i++){	// 100보다 큰 경우 각 자리별로 등차수열을 이루는지 계산 후
			int h = i / 100;
			int t = (i - (h * 100)) / 10;
			int o = i - (h * 100) - (t * 10);
			
			cnt += h - t == t - o ? 1 : 0;		// 만약 등차수열이라면? cnt + 1, 아니면 그냥 다음수 진행
		}
		
		return cnt;								// 최종 한수의 수를 반환
	}
}
