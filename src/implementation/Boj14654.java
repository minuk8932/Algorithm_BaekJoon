package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14654번: 스테판 쿼리
 *
 *	@see https://www.acmicpc.net/problem/14654/
 *
 */
public class Boj14654 {
	private static boolean[] flag;
	private static int win;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] round = new int[N][2];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			round[i][0] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			round[i][1] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(N == 1 ? 1: count(N, round));
	}
	
	private static int count(int n, int[][] arr) {
		int max = 0;
		flag = new boolean[2];
		
		for(int i = 0; i < n; i++) {
			if(arr[i][0] > arr[i][1]) {
				if(arr[i][0] == 3 && arr[i][1] == 1) max = getMax(max, 1, 0);	// 가위 : 보
				else max = getMax(max, 0, 1);
			}
			else if(arr[i][0] < arr[i][1]){
				if(arr[i][0] == 1 && arr[i][1] == 3) max = getMax(max, 0, 1);	// 가위 : 보
				else max = getMax(max, 1, 0);
			}
			else {							// 무승부
				max = Math.max(win, max);
				
				if(flag[1]) draw(0, 1);
				else draw(1, 0);

				win = 1;
			}
		}
		
		return max;
	}
	
	private static int getMax(int value, int w, int l) {
		if(flag[w]) {
			win++;
			value = Math.max(win, value);
		}
		else {
			value = Math.max(win, value);
			win = 1;
		}
		
		flag[w] = true;
		flag[l] = false;
		
		return value;
	}
	
	private static void draw(int w, int l) {
		flag[w] = true;
		flag[l] = false;
	}
}
