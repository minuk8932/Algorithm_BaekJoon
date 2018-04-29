package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2622번 : 삼각형만들기
 *
 *	@see https://www.acmicpc.net/problem/2622/
 *
 */
public class Boj2622 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int line = N / 3;									// 최소 길이의 갯수
		int result = 0;
		
		for(int i = 1; i < line + 1; i++){				// 최소 길이는 가지는 변에 대해 하나씩 반복문을 수행
			for(int j = i; j < (N - i) / 2 + 1; j++){	// 중간 길이를 가질 수 있는 변의 길이를 구함
				int minAddMid = i + j;						// 최소와 중간길이를 더하
				int max = N - minAddMid;				// 그 값을 뺀 최대 변을 구함
				
				if (minAddMid > max) {				// 삼각형의 정의 이용
		            result++;								// 삼각형 갯수 +1
		        }
			}
		}
		System.out.println(result);				// 결과값 출력
	}
}
