package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1712번: 손익분기점
 *	
 *	@see https://www.acmicpc.net/problem/1712/
 *
 */
public class Boj1712 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long loss = Long.parseLong(st.nextToken());			// 고정 비용
		long vCost = Long.parseLong(st.nextToken());		// 가변 비용
		long profit = Long.parseLong(st.nextToken());		// 1대 판매가
		
		long diff = profit - vCost;					// 판매가와 가변비용의 차이를 구하고
		
		if(diff > 0){				// 차이 > 0 인경우
			loss /= diff;			// 몫을 구하고 +1은 아래 출력에서.. 
		}
		else{
			loss = -2;				// 차이 <= 0 인경우 손익분기점에 도달 할 수 없으므로 -1, 아래에서 ++연산이 있으므로 -2를 대입
		}
		
		System.out.println(++loss);		// 결과값 출력
	}
}
