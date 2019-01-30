package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2992번: 크면서 작은 수
 *
 *	@see https://www.acmicpc.net/problem/2992/
 *
 */
public class Boj2992 {
	private static boolean[] isVisited;
	private static int min = Integer.MAX_VALUE, target;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		char[] nums = num.toCharArray();
		
		target = Integer.parseInt(num);
		
		for(int i = 0; i < nums.length; i++) {		// 각 자리별 탐색
			isVisited = new boolean[nums.length];
			backTracking(nums, 0, i, nums[i] + "");
		}
		
		System.out.println(min == Integer.MAX_VALUE ? 0 : min);		// 구성이 같으면서 원래보다 크고 최소인 수가 없으면 0
	}
	
	private static void backTracking(char[] arr, int depth, int current, String str) {
		if(isVisited[current]) return;
		isVisited[current] = true;
		
		if(arr.length - 1 == depth) {			// 구성이 같아졌을때
			int tmp = Integer.parseInt(str);
			
			if(tmp > target) {
				if(min > tmp) min = tmp;		// 타겟보다 큰 최솟 값 저장
			}
			
			return;
		}
		
		for(int next = 0; next < arr.length; next++) {
			if(isVisited[next]) continue;
			
			backTracking(arr, depth + 1, next, str + arr[next]);
			isVisited[next] = false;							// 백트래킹
		}
	}
}
