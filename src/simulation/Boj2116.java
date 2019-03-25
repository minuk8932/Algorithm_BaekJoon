package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2116번: 주사위 쌓기
 *
 *	@see https://www.acmicpc.net/problem/2116/
 *
 */
public class Boj2116 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] dice = new int[N][6];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 6; j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(stacking(N, dice));
	}
	
	private static int stacking(int n, int[][] arr) {
		int max = 0;
		boolean[] isExcepted = new boolean[7];
		
		for(int floor = 0; floor < 6; floor++) {
			int sum = 0, ceil = getAnother(floor);
			
			isExcepted[arr[0][floor]] = isExcepted[arr[0][ceil]] = true;
			
			for(int i = 0; i < 6; i++) {
				if(isExcepted[arr[0][i]]) {
					isExcepted[arr[0][i]] = false;
					continue;
				}
				
				if(sum < arr[0][i]) sum = arr[0][i];			// floor와 ceil 결정 후 0번 주사위 옆면의 최댓값
			}

			for(int i = 1; i < n; i++) {						// 1 ~ n-1번 주사위 까지
				int index = 0;
				
				for(int j = 0; j < 6; j++) {
					if(arr[i - 1][ceil] == arr[i][j]) {
						index = j;
						break;
					}
				}
				
				ceil = getAnother(index);
				isExcepted[arr[i][ceil]] = isExcepted[arr[i][index]] = true;		// 각 주사위마다 천장과 바닥
				
				int partiteMax = 0;
				
				for(int j = 0; j < 6; j++) {
					if(isExcepted[arr[i][j]]) {
						isExcepted[arr[i][j]] = false;
						continue;
					}
					
					if(partiteMax < arr[i][j]) partiteMax = arr[i][j];
				}

				sum += partiteMax;				// 각 경우별 부분 합
			}

			if(sum > max) max = sum;
		}
		
		return max;
	}
	
	private static int getAnother(int source) {
		int another = -1;
		switch (source){
		case 0:
			another = 5;
			break;
			
		case 1:
			another = 3;
			break;
			
		case 2:
			another = 4;
			break;
			
		case 3:
			another = 1;
			break;
			
		case 4:
			another = 2;
			break;
			
		case 5:
			another = 0;
			break;
		}
		
		return another;
	}
}
