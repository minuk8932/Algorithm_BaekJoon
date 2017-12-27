package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author minchoba
 *	백준 1977번 : 완전 제곱수
 *
 *	@see https://www.acmicpc.net/problem/1977
 *
 */

public class Boj1977 {
	private static final int MAX = 102;
	private static final int NONE = -1;
	
	private static final double ZERO = 0.0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[MAX];
		
		double sqrtM = Math.sqrt(M);
		double sqrtN = Math.sqrt(N);
		int start = (int) Math.sqrt(M);
		int end = (int) Math.sqrt(N);
		
		double spare = sqrtM - start;			// M의 소숫점 자리의 값을 구함
		
		if(spare != ZERO){							// 소수점 자리가 0 일 경우 : M은 원래 제곱수	(N일 경우엔 무시해도 됨)
			start++;									// 0이 아니라면, a.xxxx 인데, a보다는 크고 a+1보다 작은 제곱수 이므로 정수형 M값에 1을 더해줌
		}
		
		if(sqrtM != sqrtN && start <= end){				// if(두 수가 같은 수의 제곱수가 아니면서, 동시에 start <= end)
			int total = 0;
			int tmp = start;
			
			for(int i = 1; i <= end - start + 1; i++){		// start <= x <= end 에 해당하는 값들을 제곱해 arr 배열에 넣으며 총합을 구함
				arr[i] = (int)Math.pow(tmp, 2);
				tmp++;
				total += arr[i];
			}
			
			System.out.println(total);						// 총합
			System.out.println(arr[1]);						// 가장 작은 결과 값
			
		}
		else {
			if(spare == ZERO){									// N, M이 모두 제곱수이면서 같은 값을 갖는 경우
				System.out.println(M);
				System.out.println(M);
			}
			else{
				System.out.println(NONE);					// 사이에 제곱수가 없거나 구할 수 없는 경우 : -1
			}
		}	
	}
}
