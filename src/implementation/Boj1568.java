package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1568번: 새
 *
 *	@see https://www.acmicpc.net/problem/1568/
 *
 */
public class Boj1568 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int sec = 0;
		int flyAway = 0;
		
		while(N > 0) {
			if(N > flyAway) flyAway++;	// 날아가는 새의 수를 하나씩 더함
			else flyAway = 1;			// 만약 날아갈 새의 수가 남은 수보다 적거나 같은경우, 날아갈 수를 1로 초기화
			
			N -= flyAway;			
			
			sec++;			// 모두 날아가는데 걸린 시간
		}
		
		System.out.println(sec);		// 결과 값 출력
	}
}
