package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10040 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] most = new int[N+1];
		int[] cost = new int[M+1];
		int[] vote = new int[N+1];
		
		for(int i = 1; i <= N; i++){
			most[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 1 ; i <= M; i++){
			cost[i] = Integer.parseInt(br.readLine());
		}
		
		
		for(int i = 1; i <= M; i++){
			int comp = 1000;
			for(int j = 1; j <= N; j++){
				if(most[j] <= cost[i]){
					comp = Math.min(comp, j);
				}
				
			}
			vote[comp]++;
		}
		
		int res = 0, resIdx = 0;;
		for(int i = 1; i <= N; i++){
			res = Math.max(res, vote[i]);
			if(res == vote[i]){
				resIdx = i;
			}
		}
		System.out.println(resIdx);
	}
}
