package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2303번: 숫자 게임
 *
 *	@see https://www.acmicpc.net/problem/2303/
 *
 */
public class Boj2303 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] nums = new int[N][5];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < nums[i].length; j++)	nums[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int res = 0;
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			int tmp = getMax(nums[i]);		// 메소드를 통해 해당 배열에서의 가장 큰 1의 자리 값을 가져옴
			
			if(tmp >= max) {		// 1의 자리의 값이 가장 큰 것 중 제일 큰 번호를 가지는 값을 저장
				max = tmp;
				res = i + 1;
			}
		}
		
		System.out.println(res);		// 결과 값 출력
	}
	
	/**
	 * 최대 값 반환 메소드
	 * 
	 */
	private static int getMax(int[] arr) {
		int tmp = 0;
		
		for(int i = 0; i < arr.length - 2; i++) {
			for(int j = i + 1; j < arr.length - 1; j++) {
				if(i == j) continue;
				
				for(int k = j + 1; k < arr.length; k++) {
					if(j == k || i == k) continue;
					int num = (arr[i] + arr[j] + arr[k]) % 10;		// 중복없이 5C3개의 합을 구해 1의자리만 저장 후
					
					if(num > tmp) tmp = num;			// 그 중 최댓값을 받음
				}
			}
		}
		
		return tmp;
	}
}
