package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2576번 : 홀수
 *
 *	@see https://www.amicpc.net/problem/2576/
 *
 */
public class Boj2576 {
	private static final int INF = 101;
	private static final int NO_ODD = -1;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] isOdd = new boolean[INF];						// 홀수 체크 배열
		int loop = 7, hasOdd = 0, sum = 0;							// 반복횟수, 홀수가 아예없는 경우를 확인할 변수, 홀수의 합
		
		while(loop-- > 0){
			int num = Integer.parseInt(br.readLine());
			
			if(num % 2 == 1){			// 받아온 숫자가 홀수일 경우
				isOdd[num] = true;		// 해당 인덱스를 가진 배열을 참으로 바꾸고
				sum += num;				// 홀수 합을 저장
				
				hasOdd++;					// 홀수 존재 +1
			}
		}
		
		int min = 0;
		
		for(int i = 1; i < INF; i++){
			if(isOdd[i]){
				min = i;						// 최소 인덱스를 해당 변수에 담고
				break;
			}
		}
		
		System.out.println(hasOdd == 0 ? NO_ODD : sum + NEW_LINE + min);		// 만약 홀수가 존재하지 않는경우 -1을 존재한다면 홀수의 총 합과 최소값을 가지는 홀수를 출력
	}
}
