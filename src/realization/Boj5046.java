package realization;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Boj5046 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stCase = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(stCase.nextToken());	// 인원
		int B = Integer.parseInt(stCase.nextToken());	// 예산
		int H = Integer.parseInt(stCase.nextToken());	// 호텔
		int W = Integer.parseInt(stCase.nextToken());	// 고를수 있는 week의 개수
		int[] a = new int[W];
		int[][] cost = new int[H][W];
		
		
		for(int i = 0; i < H; i++){
			int p = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < W; j++){
				
				a[j] = Integer.parseInt(st.nextToken());
				if(a[j] >= N){
					cost[i][j] = p;
				}
				else{
					cost[i][j] = 10000;
				}
			}
		}
		
		int min = 10000;
		for(int i = 0; i < H; i++){
			for(int j = 0; j < W; j++){
				min = Math.min(min, cost[i][j]);
			}
		}
		
		
		if(min * N <= B){
			System.out.println(min * N);
		}
		else{
			System.out.println("stay home");
		}
		
		
	}

}
