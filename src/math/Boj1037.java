package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 *	백준 1037번 : 약수
 */

public class Boj1037 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통해 입력을 받음
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N + 1];			// 1과 N을 제외한 모든 약수를 받을 배열
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);	//	공백으로 구분해 약수를 받아옴
		for(int i = 1; i < N + 1; i++){
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);		// 오름차순 정렬
		
		System.out.println(nums[1] * nums[N]);	/**
																	*		
																	*		어떤 수 N의 약수들을 절반으로 나누어 각각 끝부터 곱하면 그 결과는 모두 어떤 수 N이 된다.
																	*		ex) N의 약수 n1, n2, n3, n4, n5
																	*				-> N = n1 * n5 = n2 * n4 = n3^2
																	*/
	}
}
