package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1233번: 주사위
 *
 *	@see https://www.acmicpc.net/problem/1233/
 *
 */
public class Boj1233 {
	private static final int MAX = 81;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S1 = Integer.parseInt(st.nextToken());
		int S2 = Integer.parseInt(st.nextToken());
		int S3 = Integer.parseInt(st.nextToken());
		
		int[] nums = new int[MAX];
		
		for(int i = 1; i < S1 + 1; i++) {
			for(int j = 1; j < S2 + 1; j++) {
				for(int k = 1; k < S3 + 1; k++) {
					nums[i + j + k]++;				// 각 합의 경우를 모두 구해 그에 해당하는 인덱스의 값 +1
				}
			}
		}
		
		int max = 0;		
		int res = 0;
		
		for(int i = MAX - 1; i > 2; i--) {
			if(nums[i] > max) max = nums[i];	// 최대 등장하는 수의 인덱스를 계속 갱신하며
			if(nums[i] == max) res = i;			// 그때 최대 등장하는 횟수가 같다면, 역방향 반복문을 통해 그중 가장 최솟값을 결과 변수에 저장
		}
		
		System.out.println(res);		// 결과값 출력
	}
}
