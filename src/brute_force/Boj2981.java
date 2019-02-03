package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * 	@author minchoba
 *	백준 2981번: 검문
 *
 *	@see https://www.acmicpc.net/problem/2981/
 *
 */
public class Boj2981 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(getM(nums));
	}
	
	private static StringBuilder getM(int[] arr) {
		StringBuilder sb = new StringBuilder();
		Arrays.sort(arr);
		
		ArrayList<Integer> gcd = new ArrayList<>();
		int target = arr[arr.length - 1] - arr[0];		// (arr[n - 1] - arr[0]) = M(x[n - 1] - arr[0])
		gcd.add(target);
		
		for(int i = 2; i < Math.sqrt(target) + 1; i++) {
			if(target % i == 0) {
				if(!gcd.contains(i)) gcd.add(i);
				if(!gcd.contains(target / i)) gcd.add(target / i);		// 중복없이 약수 구하기
			}
		}
		
		Collections.sort(gcd);
		int size = gcd.size();
		
		for(int idx = 0; idx < size; idx++) {
			int g = gcd.get(idx);
			int flag = arr[0] % g;
			boolean allSame = true;
			
			for(int i = 1; i < arr.length; i++) {
				if(flag != arr[i] % g) {
					allSame = false;
					break;
				}
			}
			
			if(allSame) sb.append(g).append(SPACE);		// 모두 같은 경우만
		}
		
		return sb;
	}
}
