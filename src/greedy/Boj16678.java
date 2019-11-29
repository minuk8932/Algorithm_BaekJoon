package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author exponential-e
 *	백준 16678번: 모독
 *
 *	@see https://www.acmicpc.net/problem/16678/
 *
 */
public class Boj16678 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] person = new int[N];
		
		for(int i = 0; i < N; i++) {
			person[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.parallelSort(person);
		System.out.println(hacker(N, person));
	}
	
	private static long hacker(int n, int[] arr) {
		long count = 0;
		int val = 1;
		
		for(int i = 0; i < n; i++){
	        if(arr[i] >= val){
	            count += arr[i] - val;				// hire neccesary count
	            val++;
	        }
	    }
		
		return count;
	}
}
