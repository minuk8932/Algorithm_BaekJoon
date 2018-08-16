package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10817번: 세 수
 *
 *	@see https://www.acmicpc.net/problem/10817/
 *
 */
public class Boj10817 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] nums = new int[3];
		
		for(int i = 0; i < nums.length; i++) nums[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(nums);
		
		System.out.println(nums[1]);		// 결과 값 출력
	}
}
