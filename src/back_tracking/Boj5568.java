package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * 
 * 	@author minchoba
 *	백준 5668번: 카드 놓기
 *
 *	@see https://www.acmicpc.net/problem/5568/
 *
 */
public class Boj5568 {
	private static HashSet<String> hs = new HashSet<>();
	private static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int[] nums = new int[n];
		isVisited = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i < n; i++) {
			backTracking(nums, k, 0, i, "");
		}
		
		System.out.println(hs.size());
	}
	
	private static void backTracking(int[] arr, int limit, int depth, int current, String num) {
		if(depth == limit) {
			if(!hs.contains(num)) hs.add(num);			// 중복되지 않는 숫자만 저장		
			return;
		}
		
		if(arr.length == current) return;
		
		for(int next = 0; next < arr.length; next++) {
			if(isVisited[next] || next == current) continue;
			isVisited[next] = true;
			
			backTracking(arr, limit, depth + 1, next, num + arr[next]);		// 숫자를 이어 붙이며 재귀 호출
			isVisited[next] = false;									// 제한 조건에 걸릴시 백트래킹
		}
	}
}
