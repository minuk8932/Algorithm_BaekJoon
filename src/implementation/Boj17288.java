package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17288번: 3개만!
 *
 *	@see https://www.acmicpc.net/problem/17288/
 *
 */
public class Boj17288 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] nums = br.readLine().toCharArray();
		
		System.out.println(counting(nums));
	}
	
	private static int counting(char[] arr) {
		int count = 0;
		int size = 1, prev = arr[0];
		
		for(int i = 1; i < arr.length; i++) {			
			if(prev + 1 == arr[i]) size++;		// 오름차순인 경우 크기 +1
			else size = 1;						// 아닌 경우 초기화
			
			prev = arr[i];

			if(size == 3) {
				if(i < arr.length - 1 && arr[i + 1] == arr[i] + 1) continue;		// 크기가 3인데 뒤에 남아있는 원소도 오름차순으로 포함되는 경우
				count++;
			}
		}
		
		return count;
	}
}
