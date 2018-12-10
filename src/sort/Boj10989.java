package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10989번: 수 정렬하기 3
 *
 *	@https://www.acmicpc.net/problem/10989
 *
 */
public class Boj10989 {
	private static final String NEW_LINE = "\n";
	private static final int MAX = 10_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		int[] nums = new int[MAX];
		for(int i = 1 ; i < N + 1; i++){
			nums[Integer.parseInt(br.readLine())]++;
		}
		br.close();
		
		for(int i = 1; i < MAX; i++){
			if(nums[i] != 0){
				while(nums[i]-- > 0){
					sb.append(i).append(NEW_LINE);
				}
			}
		}
		System.out.println(sb.toString());
	}
}
