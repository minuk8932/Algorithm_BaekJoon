package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author minchoba
 *	백준 10610번 : 30
 *
 *	@see https://www.acmicpc.net/problem/10610
 *
 */
public class Boj10610 {
	private static final String UNREAL = "-1";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nums = br.readLine();
		
		char[] num = nums.toCharArray();
		int[] mid = new int[num.length];
		
		for(int i = 0; i < num.length; i++){			// 문자를 정수형으로
			mid[i] = -(num[i] - '0');
		}
		
		System.out.println(isThirty(mid));
	}
	
	private static String isThirty(int[] arr) {
		Arrays.sort(arr);
		
		if(arr[arr.length - 1] != 0) return UNREAL;		// 10의 배수가 아닌 경우
		int sum = 0;
		
		for(int i = 0; i < arr.length; i++) {
			sum -= arr[i];
		}
		
		if(sum % 3 != 0) return UNREAL;					// 3의 배수가 아닌 경우
		StringBuilder result = new StringBuilder();
		
		for(int i = 0; i < arr.length; i++) {
			result.append(-arr[i]);
		}
		
		return result.toString();
	}
}
