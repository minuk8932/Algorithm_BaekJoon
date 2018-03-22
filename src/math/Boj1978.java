package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1978번 : 소수 찾기
 *
 *	@see https://www.acmicpc.net/problem/1978/
 *
 */
public class Boj1978 {
	private static final String SPACE = " ";
	private static final int MAX = 1_000;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
        br.close();

		int idx = 2;

		int[] prime = new int[MAX];
		prime[0] = 2;
		prime[1] = 3;
		
		for (int i = 4; i < MAX + 1; i++) {			// 에라토스테네스의 체 알고리즘 : N까지 소수의 갯수를 구할 때, 2부터 Math.sqrt(N) 까지의 값만 나누어 보면 모든 소수가 걸러짐
			int loop = (int) Math.sqrt(i) + 1;
			boolean isPrime = true;

			for (int j = 2; j < loop; j++) {
				if(i % j == 0){								// 소수가 아니면
					isPrime = false;						// 거짓으로 바꿔주고
					break;									// 내부 반복문 종료
				}
			}
			
			if(isPrime){									// 소수라면
				prime[idx] = i;							// 배열에 담고 인덱스 값을 늘려줌
				idx++;										
			}
		}
		
		int res = 0;
		for(int compare : nums){					// 각각의 배열끼리 비교해서, 소수 배열에 해당 수가 존재하면 결과값 + 1
			for(int num : prime){
				if(compare == num){
					res++;
					
					break;
				}
			}
		}
		System.out.println(res);						// 결과값 출력
	}
}
