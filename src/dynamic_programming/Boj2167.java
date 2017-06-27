package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2167 {
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for(int i = 0 ; i < N; i++){
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++){
				arr[i][j] = Integer.parseInt(st1.nextToken());
			}
		}
		int k = Integer.parseInt(br.readLine());
		
		while(k > 0){
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st2.nextToken());
			int q = Integer.parseInt(st2.nextToken());
			int x = Integer.parseInt(st2.nextToken());
			int y = Integer.parseInt(st2.nextToken());
			int sum = 0;
			
			for(int i = p-1; i < x; i++){
				for(int j = q-1; j < y; j++){
					sum += arr[i][j];
				}
			}
			sb.append(sum).append(NEW_LINE);
				
			k--;
		}
		System.out.println(sb.toString());
	}

}
