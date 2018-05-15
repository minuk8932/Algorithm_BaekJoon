package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15734번: 명장 남정훈
 *
 *	@see https://www.acmicpc.net/problem/15734/
 *
 */
public class Boj15734 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());		// 왼발 잡이
		int R = Integer.parseInt(st.nextToken());		// 오른발 잡이
		int A = Integer.parseInt(st.nextToken());		// 양발 잡이
		
		int min = Math.min(L, R);		// 더 적은 수를 추출
		int max = Math.max(L, R);	// 더 많은 수를 추출
		int res = 0;
		
		if(L == R){							// 왼쪽 오른쪽이 같은 경우
			res = (L + A / 2) * 2;
		}
		else{
			while(max != min){			// 왼, 오 모두 값이 같아지는 경우까지 반복문 실행
				if(A == 0){			// 양발 잡이선수를 더이상 배치 못할 경우 정지
					break;
				}
				
				min++;			// 더 적은 선수의 수를 계속 양발잡이로 채워줌
				A--;
			}
			
			while(A != 1){		// 양발잡이가 1이 될때 까지 또는 0 전까지 반복문 실행
				if(A < 1){
					break;
				}
				
				min++;			// 양발잡이 선수가 없어질 때 까지, 왼발 오른발 선수를 하나씩 채워줌
				max++;
				A -= 2;
			}
			
			min = Math.min(min, max);	// 최종 최소값을 가져와서 2배를하면 최종 남는 선수
			
			res = min * 2;
		}
		
		System.out.println(res);		// 최종 선수 인원 출력
	}
}
