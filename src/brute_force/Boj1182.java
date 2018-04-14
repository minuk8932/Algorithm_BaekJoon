package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1182번 : 부분집합의 합
 */
public class Boj1182 {
	private static final String SPACE = " ";
	
	private static int[] nums = null;
	private static int N = 0;
	private static int S = 0;
	private static int res = 0;
	private static final int INF = 21;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		nums = new int[INF];
		st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 0; i < N; i++){
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		getSetCount(0, 0);				// getSetCount 메소드 호출
		System.out.println(res);		// 결과값 출력
	}
	
	/**
	 * 부분집합의 합이 같은 것의 갯수를 카운트
	 * 	@param cnt : 함수가 진행되는 횟수
	 * 	@param sum : 
	 */
	public static void getSetCount(int cnt, int sum){
		if(cnt == N){						// 집합 원소 수 만큼 다 돌면 메소드 종료
			return;
		}
		
		if(sum + nums[cnt] == S){	// 부분집합의 합이 찾으려는 S의 합을 가진 수라면, 결과 +1
			res++;
		}
		
		getSetCount(cnt + 1, sum);						// 카운트를 1 올리고, 메소드 실행 후 연산된 합을 다시 메소드에 넣고 recursion
		getSetCount(cnt + 1, sum + nums[cnt]);	// 카운트를 1 올리고, 메소드 실행 후 연산된 합에 다음 집합의 원소를 또 더해주면서 recursion
		// 위의 연산을 통해 각 부분집합마다의 합을 구해낼 수 있다.
	}
}
