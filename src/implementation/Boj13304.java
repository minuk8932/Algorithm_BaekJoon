package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 13304번: 방 배정
 *
 *	@see https://www.acmicpc.net/problem/13304/
 *
 */
public class Boj13304 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] assign = new int[5];
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());
			
			if(grade == 1 || grade == 2) {			// 1, 2학년 성별 구분 없음
				assign[0]++;
			}
			else if(grade == 3 || grade == 4) {		// 3, 4 학년 남/녀 따로
				if(gender == 1) assign[1]++;
				else assign[2]++;
			}
			else {									// 5, 6 학년 남/녀 따로
				if(gender == 1) assign[3]++;
				else assign[4]++;
			}
		}
		
		System.out.println(roomCount(assign, K));
	}
	
	private static int roomCount(int[] arr, int k) {
		int count = 0;
		
		for(int i = 0; i < arr.length; i++) {
			count += arr[i] / k;
			if(arr[i] % k > 0) count++;
		}
		
		return count;
	}
}
