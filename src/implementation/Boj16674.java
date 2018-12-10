package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16674번: 2018년을 돌아보며
 *
 *	@see https://www.acmicpc.net/problem/16674/
 *
 */
public class Boj16674 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] nums = br.readLine().toCharArray();
		
		int[] year = countingNums(nums);
		System.out.println(whatTheNums(year));
	}
	
	private static int[] countingNums(char[] arr) {
		int[] chk = new int[10];
		for(char w: arr) {
			chk[w - '0']++;
		}
		
		return chk;
	}
	
	private static int whatTheNums(int[] arr) {
		boolean[] isVisited = numCheck(arr);
		
		for(int i = 0; i < isVisited.length; i++) {				// 미리 2018을 제외하고 다른 수가 하나라도 섞여있다면 제외
			if(i == 0 || i == 1 || i == 2 || i == 8) continue;
			if(isVisited[i]) return 0;
		}
		
		if(isVisited[2] && isVisited[0] && isVisited[1] && isVisited[8]){
			if(arr[2] == arr[0] && arr[0] == arr[1] && arr[1] == arr[8]) return 8;
			else return 2;
		}
		else {
			if(arr[2] > 0 || arr[0] > 0 || arr[1] > 0 || arr[8] > 0) return 1;
			else return 0;
		}
	}
	
	private static boolean[] numCheck(int[] arr) {
		boolean[] chk = new boolean[10];
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == 0) continue;
			
			chk[i] = true;
		}
		
		return chk;
	}
}
