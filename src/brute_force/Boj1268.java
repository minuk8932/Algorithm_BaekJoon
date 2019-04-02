package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1268번: 임시 반장 정하기
 *
 *	@see https://www.acmicpc.net/problem/1268/
 *
 */
public class Boj1268 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] student = new int[N][5];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				student[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(getCpt(N, student));
	}
	
	private static int getCpt(int n, int[][] arr) {
		int[] friends = new int[n];
		
		for(int i = 0; i < n; i++) {	
			boolean[] check = new boolean[n];
			
			for(int k = 0; k < 5; k++) {				
				for(int j = 0; j < n; j++) {
					if(i == j || check[j]) continue;
					
					if(arr[i][k] == arr[j][k]) {
						friends[i]++;				// 중복 없는 친구의 수
						check[j] = true;
					}
				}
			}
		}
		
		int max = 0;
		for(int i = 0; i < n; i++) {
			if(friends[i] > max) max = friends[i];
		}
		
		int result = 0;
		for(int i = 0; i < n; i++) {
			if(friends[i] == max) {			// 반장후보 중 번호가 작은 학생
				result = i + 1;
				break;
			}
		}
		
		return result;
	}
}
